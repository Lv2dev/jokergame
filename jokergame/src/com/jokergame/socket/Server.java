package com.jokergame.socket;

import java.io.*;
import java.net.*;
import java.util.*;

//������ ��� ����Ǵ� server 
public class Server {
	private ArrayList<String> rankList = new ArrayList<String>();
	private ServerSocket server;
	private BManager bMan = new BManager(); // �޽��� �����

	public Server() {
	} // ������

	void startServer() {
		try {
			server = new ServerSocket(7777); // 7777��Ʈ
			System.out.println("���������� �����Ǿ����ϴ�.");
			while (true) {
				// Ŭ���̾�Ʈ�� ����� �����带 ��´�.
				Socket socket = server.accept();
				// �����带 ����� �����Ŵ
				JokerThread jt = new JokerThread(socket);
				jt.start();
				// �޽��� ����ڿ� �����带 �߰�
				bMan.add(jt);
				System.out.println("������ ��:" + bMan.size());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Ŭ���̾�Ʈ�� ����ϴ� ������ Ŭ����
	class JokerThread extends Thread {
		private String memberId = null; // ����� ID
		private String nickname = null; // ����� �г���
		private String selectCard = null; // ������ ī��
		ArrayList<String> cards = new ArrayList<String>(); // ī���
		private String picMemberId = null; // �Ǽ������� ID
		private Socket socket; // ����

		private BufferedReader reader; // �Է� ��Ʈ��
		private PrintWriter writer; // ��� ��Ʈ��

		JokerThread(Socket socket) { // ������
			this.socket = socket;
		}

		// ������ ��ȯ
		Socket getSocket() {
			return socket;
		}

		// ���̵� ��ȯ
		String getMemberId() {
			return memberId;
		}
		
		// ������ ī�带 ����
		void setSelectCard(String selectCard) {
			this.selectCard = selectCard;
		}
		
		// ������ ī�带 ��ȯ
		String getSelectCard() {
			return selectCard;
		}

		// �г����� ��ȯ
		String getNickname() {
			return nickname;
		}
		
		//�Ǽ��������� ����
		void setPicMemberId(String id) {
			this.picMemberId = id;
		}
		
		//�Ǽ��������� ������
		String getPicMemberId() {
			return picMemberId;
		}
		
		//ī�带 ����
		void setCards(ArrayList<String> list) {
			this.cards = list;
		}
		
		//ī����� ������
		ArrayList<String> getCards(){
			return cards;
		}
		
		//ī�带 �߰�
		void addCard(String card) {
			cards.add(card);
		}
		
		//i��°�� ī�带 ������
		String getCard(int i) {
			String temp = cards.get(i);
			cards.remove(i);
			return temp;
		}
		
		//ī�带 � ������ �ִ��� ������
		int getCardCount() {
			return cards.size();
		}

		@Override
		public void run() {
			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream(), true);

				String msg; // Ŭ���̾�Ʈ�� �޽���

				while ((msg = reader.readLine()) != null) {

					// [MEMBERID] : ���̵� ����
					if (msg.startsWith("[ID]")) {
						memberId = msg.substring(11);
					}
					// [NICKNAME] : �г��� ����
					if (msg.startsWith("[NICKNAME]")) {
						nickname = msg.substring(11);
					}
					// [ROOM] : �濡 ����
					if (msg.startsWith("[ROOM]")) {
						//���� �ο��� Ȯ��
						if(bMan.isFull()) { //���� á����
							//ī�带 ����
							bMan.shuffle();
							//��ü �����忡�� ���ӽ��� ������ ����
							bMan.sendMessage("[START]"); 
							//��ü �����忡�� ���� ���� ����
							bMan.sendSeq();
							//��ü �����忡�� �г��� ���� ���� ����
							bMan.sendNickname();
							//��� ������鿡�� �� �������� ī�� ���� ����
							
							//�� �����忡�� ī������ ����
							bMan.sendCards();
							//ù��° �����忡�� ���� ��ȣ ������
							bMan.sendTo(0, "[READY]");
						}
					}
					// [SELECT] : ������
					if (msg.startsWith("[SELECT]")) {
						String[] tempStr = msg.split(" ");
						setPicMemberId(tempStr[0].substring(9)); //�Ǽ����� ���̵� �߰�
						if(tempStr[1].startsWith("[CARD]")) {
							setSelectCard(tempStr[2].substring(7)); //������ ī�� �߰�
						}
						//��� Ŭ���̾�Ʈ���� ������ ����
						bMan.sendMessage("[SELECT]" + getMemberId() + "[CARD]" + getSelectCard());
					}
					// [GET] : ���������� ���ô� ī�� ��������
					if(msg.startsWith("[GET]")) {
						//���ô� ī�� �����ͼ� ��
						bMan.pickCard(getMemberId(), getPicMemberId(), Integer.parseInt(getSelectCard()));
						//���������� ���õ��������� ī������ ����
						bMan.sendCardsTo(getMemberId());
						bMan.sendCardsTo(getPicMemberId());
						//��� �������� ī�� �������� ����
						bMan.sendCardCount();
						
					}
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	// �޽����� �����ϴ� Ŭ����
	class BManager extends Vector {
		BManager() {
		}

		// �����带 �߰�
		void add(JokerThread jt) {
			super.add(jt);
		}

		// �����带 ����
		void remove(JokerThread jt) {
			super.remove(jt);
		}

		// i��° �����带 ��ȯ
		JokerThread getJt(int i) {
			return (JokerThread) elementAt(i);
		}

		// i��° �������� ������ ��ȯ�Ѵ�
		Socket getSocket(int i) {
			return getJt(i).getSocket();
		}

		// i��° ������� ����� Ŭ���̾�Ʈ���� �޽����� �����Ѵ�
		void sendTo(int i, String msg) {
			try {
				PrintWriter pw = new PrintWriter(getSocket(i).getOutputStream(), true);
				pw.println(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// ��ü���� �޽����� �����Ѵ�
		void sendMessage(String msg) {
			for (int i = 0; i < size(); i++) {
				sendTo(i, msg);
			}
		}

		// ���� ���� á���� �˾ƺ���
		synchronized boolean isFull() {
			int cnt = 0; // ���� �� �ο����� �����ͼ� ���⿡ �ʱ�ȭ
			if (cnt == size()) { // ���� �� �ο����� ���� �ο����� ������
				return true;
			}
			return false;
		}
		// ī�带 ��� �� ������鿡 ����
		synchronized void shuffle() {
			String[] temp = { //ī����� �迭�� ����
					"��A", "��2", "��3", "��4", "��5", "��6", "��7", "��8", "��9", "��10", "��J", "��Q", "��K", 
					"��A", "��2", "��3", "��4", "��5", "��6", "��7", "��8", "��9", "��10", "��J", "��Q", "��K", 
					"��A", "��2", "��3", "��4", "��5", "��6", "��7", "��8", "��9", "��10", "��J", "��Q", "��K", 
					"��A", "��2", "��3", "��4", "��5", "��6", "��7", "��8", "��9", "��10", "��J", "��Q", "��K", 
					"JOKER"
			};
			ArrayList<String> allCards = new ArrayList<>(Arrays.asList(temp)); //ī����� arraylist�� ��ȯ
			//ī�� ����
			Collections.shuffle(allCards);
			//ī�� ����
			for(int i = 0; i < size(); i++) {
				//�����忡 0��° ī�� ����
				getJt(i).addCard(allCards.get(0));
				//������ ī�� ����
				allCards.remove(0);
			}
		}
		// ���� ���� �����ϱ�
		synchronized void sendSeq() {
			String seq = "[SEQ]";
			for(int i = 0; i < size(); i++) {
				seq += getJt(i).getMemberId() + " ";
			}
			sendMessage(seq);
		}
		// �г��� ���� ���� �����ϱ�
		synchronized void sendNickname() {
			String msg = "[SEQNICKNAME]";
			for(int i = 0; i< size(); i++) {
				msg += getJt(i).getNickname() + " ";
			}
			sendMessage(msg);
		}
		// �� �����忡�� ī�� ���� ����
		synchronized void sendCards() {
			for(int i = 0; i < size(); i++) {
				String msg = "[SET]";
				ArrayList<String> list = getJt(i).getCards();
				for(String tempStr : list){ //�� ��������� ī����� ������
					msg += tempStr + " ";
				}
				sendMessage(msg); //�� ������鿡�� ����
			}
		}
		// [GET] : ���ô� ī�� ��������
		synchronized void pickCard(String memberId, String picMemberId, int picCard) {
			int i = findByMemberId(picMemberId); //���õ� ������ �� ��° ���������� ������
			String card = getJt(i).getCard(picCard); //������ ������ ������ ī�带 ������(������ ī��� ���õ� ������ ������ ������)
			
			int j = findByMemberId(memberId); //���� ������ �� ��° ���������� ������
			//�������ִ� ī��� ������
			ArrayList<String> list = getJt(j).getCards();
			//�������ִ� ī���� ������ ī�带 ���ؼ� ��Ī�Ǵ� ī�尡 �ִ��� Ȯ��
			for(int y = 0; y < list.size(); y++) {
				//ī�� ���ؼ� ��Ī�Ǵ� ī�尡 �ִ� ���
				if(list.get(y).substring(1).equals(card.substring(1))) {
					list.remove(y); //��Ī�Ǵ� ī�� ����
					getJt(j).setCards(list); //��Ī�Ǵ� ī�带 ������ list�� ���� ������ �����忡 ����
					//������ ī�带 �������� ���
					if(0 == list.size()) {
						rankList.add(memberId); //��ŷ�� ����
						//�ٸ� �������� ���� ī����� üũ
						int cnt = 0;
						for(int k = 0; k < size(); k++) {
							if(getJt(k).getCardCount() != 0) {
								//ī�尡 0������ ���� ���� ����� ī��Ʈ
								cnt++;
							}
						}
						if(cnt == 1) { //ī�尡 0������ ���� ���� ����� �Ѹ��̸�
							sendMessage("[END]"); //���� ���� �޽��� ����
							return;
						}
					}
					return; //�Լ� ����
				}
			}
			//��Ī�Ǵ� ī�尡 ���� ��� ����
			list.add(card); //������ ī�带 �߰�
			Collections.shuffle(list); //����
			getJt(j).setCards(list); //ī�� �߰��ϰ� ������ list�� ���� ������ �����忡 ����
		}
		// �Ѿ�� ���̵� ���° ���������� ã��
		synchronized int findByMemberId(String memberId) {
			for(int i = 0; i < size(); i++) {
				if(memberId == getJt(i).getMemberId()) {
					return i;
				}
			}
			return 0;
		}
		// ��� �����鿡�� ��� ������ ī�� ���� ����
		synchronized void sendCardCount() {
			String msg = "[INIT]";
			for(int i = 0; i < size(); i++) {
				msg += getJt(i).getCardCount() + " ";
			}
			sendMessage(msg);
		}
		//memberId �������� ī��� ����
		synchronized void sendCardsTo(String memberId) {
			String msg = "[SET]";
			int threadNum = findByMemberId(memberId);
			ArrayList<String> list = getJt(threadNum).getCards();
			for(String tempStr : list) {
				msg += tempStr + " ";
			}
			sendTo(threadNum, msg);
		}
	}
}
