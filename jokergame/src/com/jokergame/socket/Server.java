package com.jokergame.socket;

import java.io.*;
import java.net.*;
import java.util.*;

//방장인 경우 실행되는 server 
public class Server {
	private ArrayList<String> rankList = new ArrayList<String>();
	private ServerSocket server;
	private BManager bMan = new BManager(); // 메시지 방송자

	public Server() {
	} // 생성자

	void startServer() {
		try {
			server = new ServerSocket(7777); // 7777포트
			System.out.println("서버소켓이 생성되었습니다.");
			while (true) {
				// 클라이언트와 연결된 스레드를 얻는다.
				Socket socket = server.accept();
				// 스레드를 만들고 실행시킴
				JokerThread jt = new JokerThread(socket);
				jt.start();
				// 메시지 방송자에 스레드를 추가
				bMan.add(jt);
				System.out.println("접속자 수:" + bMan.size());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 클라이언트와 통신하는 스레드 클래스
	class JokerThread extends Thread {
		private String memberId = null; // 사용자 ID
		private String nickname = null; // 사용자 닉네임
		private String selectCard = null; // 선택한 카드
		ArrayList<String> cards = new ArrayList<String>(); // 카드들
		private String picMemberId = null; // 피선택유저 ID
		private Socket socket; // 소켓

		private BufferedReader reader; // 입력 스트림
		private PrintWriter writer; // 출력 스트림

		JokerThread(Socket socket) { // 생성자
			this.socket = socket;
		}

		// 소켓을 반환
		Socket getSocket() {
			return socket;
		}

		// 아이디를 반환
		String getMemberId() {
			return memberId;
		}
		
		// 선택한 카드를 세팅
		void setSelectCard(String selectCard) {
			this.selectCard = selectCard;
		}
		
		// 선택한 카드를 반환
		String getSelectCard() {
			return selectCard;
		}

		// 닉네임을 반환
		String getNickname() {
			return nickname;
		}
		
		//피선택유저를 세팅
		void setPicMemberId(String id) {
			this.picMemberId = id;
		}
		
		//피선택유저를 가져옴
		String getPicMemberId() {
			return picMemberId;
		}
		
		//카드를 세팅
		void setCards(ArrayList<String> list) {
			this.cards = list;
		}
		
		//카드들을 가져옴
		ArrayList<String> getCards(){
			return cards;
		}
		
		//카드를 추가
		void addCard(String card) {
			cards.add(card);
		}
		
		//i번째의 카드를 가져옴
		String getCard(int i) {
			String temp = cards.get(i);
			cards.remove(i);
			return temp;
		}
		
		//카드를 몇개 가지고 있는지 가져옴
		int getCardCount() {
			return cards.size();
		}

		@Override
		public void run() {
			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream(), true);

				String msg; // 클라이언트의 메시지

				while ((msg = reader.readLine()) != null) {

					// [MEMBERID] : 아이디 저장
					if (msg.startsWith("[ID]")) {
						memberId = msg.substring(11);
					}
					// [NICKNAME] : 닉네임 저장
					if (msg.startsWith("[NICKNAME]")) {
						nickname = msg.substring(11);
					}
					// [ROOM] : 방에 들어옴
					if (msg.startsWith("[ROOM]")) {
						//방의 인원을 확인
						if(bMan.isFull()) { //가득 찼으면
							//카드를 셔플
							bMan.shuffle();
							//전체 스레드에게 게임시작 정보를 전달
							bMan.sendMessage("[START]"); 
							//전체 스레드에게 순서 정보 전달
							bMan.sendSeq();
							//전체 스레드에게 닉네임 순서 정보 전달
							bMan.sendNickname();
							//모든 스레드들에게 각 유저들의 카드 갯수 전달
							
							//각 스레드에게 카드정보 전달
							bMan.sendCards();
							//첫번째 스레드에게 시작 신호 보내기
							bMan.sendTo(0, "[READY]");
						}
					}
					// [SELECT] : 간보기
					if (msg.startsWith("[SELECT]")) {
						String[] tempStr = msg.split(" ");
						setPicMemberId(tempStr[0].substring(9)); //피선택자 아이디 추가
						if(tempStr[1].startsWith("[CARD]")) {
							setSelectCard(tempStr[2].substring(7)); //선택한 카드 추가
						}
						//모든 클라이언트에게 간보기 전달
						bMan.sendMessage("[SELECT]" + getMemberId() + "[CARD]" + getSelectCard());
					}
					// [GET] : 마지막으로 간봤던 카드 가져오기
					if(msg.startsWith("[GET]")) {
						//간봤던 카드 가져와서 비교
						bMan.pickCard(getMemberId(), getPicMemberId(), Integer.parseInt(getSelectCard()));
						//현재유저와 선택된유저에게 카드정보 전달
						bMan.sendCardsTo(getMemberId());
						bMan.sendCardsTo(getPicMemberId());
						//모든 유저에게 카드 갯수정보 전달
						bMan.sendCardCount();
						
					}
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	// 메시지를 전달하는 클래스
	class BManager extends Vector {
		BManager() {
		}

		// 스레드를 추가
		void add(JokerThread jt) {
			super.add(jt);
		}

		// 스레드를 제거
		void remove(JokerThread jt) {
			super.remove(jt);
		}

		// i번째 스레드를 반환
		JokerThread getJt(int i) {
			return (JokerThread) elementAt(i);
		}

		// i번째 스레드의 소켓을 반환한다
		Socket getSocket(int i) {
			return getJt(i).getSocket();
		}

		// i번째 스레드와 연결된 클라이언트에게 메시지를 전송한다
		void sendTo(int i, String msg) {
			try {
				PrintWriter pw = new PrintWriter(getSocket(i).getOutputStream(), true);
				pw.println(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 전체에게 메시지를 전송한다
		void sendMessage(String msg) {
			for (int i = 0; i < size(); i++) {
				sendTo(i, msg);
			}
		}

		// 방이 가득 찼는지 알아본다
		synchronized boolean isFull() {
			int cnt = 0; // 방의 총 인원수를 가져와서 여기에 초기화
			if (cnt == size()) { // 방의 총 인원수와 현재 인원수가 같으면
				return true;
			}
			return false;
		}
		// 카드를 섞어서 각 스레드들에 배포
		synchronized void shuffle() {
			String[] temp = { //카드들을 배열로 저장
					"♠A", "♠2", "♠3", "♠4", "♠5", "♠6", "♠7", "♠8", "♠9", "♠10", "♠J", "♠Q", "♠K", 
					"♥A", "♥2", "♥3", "♥4", "♥5", "♥6", "♥7", "♥8", "♥9", "♥10", "♥J", "♥Q", "♥K", 
					"◆A", "◆2", "◆3", "◆4", "◆5", "◆6", "◆7", "◆8", "◆9", "◆10", "◆J", "◆Q", "◆K", 
					"♣A", "♣2", "♣3", "♣4", "♣5", "♣6", "♣7", "♣8", "♣9", "♣10", "♣J", "♣Q", "♣K", 
					"JOKER"
			};
			ArrayList<String> allCards = new ArrayList<>(Arrays.asList(temp)); //카드들을 arraylist로 변환
			//카드 셔플
			Collections.shuffle(allCards);
			//카드 배포
			for(int i = 0; i < size(); i++) {
				//스레드에 0번째 카드 전달
				getJt(i).addCard(allCards.get(0));
				//전달한 카드 삭제
				allCards.remove(0);
			}
		}
		// 순서 정보 전달하기
		synchronized void sendSeq() {
			String seq = "[SEQ]";
			for(int i = 0; i < size(); i++) {
				seq += getJt(i).getMemberId() + " ";
			}
			sendMessage(seq);
		}
		// 닉네임 순서 정보 전달하기
		synchronized void sendNickname() {
			String msg = "[SEQNICKNAME]";
			for(int i = 0; i< size(); i++) {
				msg += getJt(i).getNickname() + " ";
			}
			sendMessage(msg);
		}
		// 각 스레드에게 카드 정보 전달
		synchronized void sendCards() {
			for(int i = 0; i < size(); i++) {
				String msg = "[SET]";
				ArrayList<String> list = getJt(i).getCards();
				for(String tempStr : list){ //각 스레드들의 카드들을 가져옴
					msg += tempStr + " ";
				}
				sendMessage(msg); //각 스레드들에게 전달
			}
		}
		// [GET] : 간봤던 카드 가져오기
		synchronized void pickCard(String memberId, String picMemberId, int picCard) {
			int i = findByMemberId(picMemberId); //선택된 유저가 몇 번째 스레드인지 가져옴
			String card = getJt(i).getCard(picCard); //선택한 유저의 선택한 카드를 꺼내옴(꺼내온 카드는 선택된 유저의 덱에서 삭제됨)
			
			int j = findByMemberId(memberId); //현재 유저가 몇 번째 스레드인지 가져옴
			//가지고있는 카드들 가져옴
			ArrayList<String> list = getJt(j).getCards();
			//가지고있는 카드들과 가져온 카드를 비교해서 매칭되는 카드가 있는지 확인
			for(int y = 0; y < list.size(); y++) {
				//카드 비교해서 매칭되는 카드가 있는 경우
				if(list.get(y).substring(1).equals(card.substring(1))) {
					list.remove(y); //매칭되는 카드 제거
					getJt(j).setCards(list); //매칭되는 카드를 제거한 list를 현재 유저의 스레드에 저장
					//마지막 카드를 내려놓은 경우
					if(0 == list.size()) {
						rankList.add(memberId); //랭킹에 저장
						//다른 유저들의 남은 카드들을 체크
						int cnt = 0;
						for(int k = 0; k < size(); k++) {
							if(getJt(k).getCardCount() != 0) {
								//카드가 0개보다 많이 남은 사람을 카운트
								cnt++;
							}
						}
						if(cnt == 1) { //카드가 0개보다 많이 남은 사람이 한명이면
							sendMessage("[END]"); //게임 종료 메시지 전달
							return;
						}
					}
					return; //함수 종료
				}
			}
			//매칭되는 카드가 없는 경우 수행
			list.add(card); //가져온 카드를 추가
			Collections.shuffle(list); //셔플
			getJt(j).setCards(list); //카드 추가하고 셔플한 list를 현재 유저의 스레드에 저장
		}
		// 넘어온 아이디가 몇번째 스레드인지 찾기
		synchronized int findByMemberId(String memberId) {
			for(int i = 0; i < size(); i++) {
				if(memberId == getJt(i).getMemberId()) {
					return i;
				}
			}
			return 0;
		}
		// 모든 유저들에게 모든 유저의 카드 갯수 전달
		synchronized void sendCardCount() {
			String msg = "[INIT]";
			for(int i = 0; i < size(); i++) {
				msg += getJt(i).getCardCount() + " ";
			}
			sendMessage(msg);
		}
		//memberId 스레드의 카드들 전달
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
