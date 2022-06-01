package com.jokergame.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Point;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.jokergame.member.MemberDAO;
import com.jokergame.member.MemberDTO;
import com.jokergame.room.RoomDAO;
import com.jokergame.room.RoomDTO;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Main {
	MemberDAO memberDAO = MemberDAO.getInstance();
	MemberDTO memberDTO;
	
	RoomDAO roomDAO = RoomDAO.getInstance();
	RoomDTO roomDTO;

	private JFrame mainFrame;
	private JTextField txtId;
	private JTextField txtPw;
	private JTextField txtJoinNick;
	private JTextField txtJoinPw;
	private JTextField txtJoinPwChk;
	private JTextField txtJoinId;
	private JTextField txtJoinQ;
	private JTextField txtJoinA;
	private JTextField txtFindPwId;
	private JTextField txtFindPwQ;
	private JTextField txtFindPwA;
	private JPasswordField txtInfoPw1;
	private JPasswordField txtInfoPw2;
	private JPasswordField txtInfoNowPw;
	private JTable tblMain;
	private DefaultTableModel dtmMain;
	private JTable tblRoom;
	private DefaultTableModel dtmRoom;
	private JTable tblRank;
	private DefaultTableModel dtmRank;
	private JTextField txtRoomName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String roomContent[][] = {};
		String roomHeader[] = { "레벨", "닉네임", "배팅경험치", "준비" };

		String inviteContent[][] = {};
		String inviteHeader[] = { "받은초대" };

		String friendContent[][] = {};
		String friendHeader[] = { "친구명", "상태" };

		String rankHeader[] = { "랭크", "닉네임", "레벨", "전적" };
		String rankContent[][] = {};

		String header[] = { "방번호", "방이름", "방장", "유저수" };
		String content[][] = {};

		mainFrame = new JFrame();
		mainFrame.setResizable(false);
		mainFrame.setSize(new Dimension(1000, 1000));
		mainFrame.setBounds(100, 100, 1000, 800);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
								
										JPanel mainPanel = new JPanel();
										
										mainPanel.setBounds(0, 0, 982, 761);
										mainFrame.getContentPane().add(mainPanel);
										mainPanel.setLayout(null);
										
												JLabel lblMain = new JLabel("Lv10 \u3147\u3147\u3147\uB2D8 \uBC18\uAC11\uC2B5\uB2C8\uB2E4");
												lblMain.setBounds(30, 30, 600, 70);
												lblMain.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 40));
												mainPanel.add(lblMain);
												
														JButton btnMainInfo = new JButton("\uD68C\uC6D0\uC815\uBCF4");
														
														btnMainInfo.setBounds(730, 30, 140, 70);
														btnMainInfo.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
														btnMainInfo.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
														mainPanel.add(btnMainInfo);
														
																JButton btnMainRank = new JButton("\uB7AD\uD0B9");
																
																btnMainRank.setBounds(580, 30, 140, 70);
																btnMainRank.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
																btnMainRank.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
																mainPanel.add(btnMainRank);
																
																		JButton btnMainFriends = new JButton("\uCE5C\uAD6C");
																		
																		btnMainFriends.setBounds(30, 660, 160, 70);
																		btnMainFriends.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
																		btnMainFriends.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
																		mainPanel.add(btnMainFriends);
																		
																				JButton btnMainMatch = new JButton("\uB79C\uB364\uB9E4\uCE6D");
																				
																				btnMainMatch.setBounds(220, 660, 160, 70);
																				btnMainMatch.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
																				btnMainMatch.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
																				mainPanel.add(btnMainMatch);
																				
																						JButton btnMainDel = new JButton("\uBC29\uC0AD\uC81C");
																						btnMainDel.setBounds(410, 660, 160, 70);
																						btnMainDel.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
																						btnMainDel.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
																						mainPanel.add(btnMainDel);
																						
																								JButton btnMainJoin = new JButton("\uB4E4\uC5B4\uAC00\uAE30");
																								
																								btnMainJoin.setBounds(790, 660, 160, 70);
																								btnMainJoin.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
																								btnMainJoin.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
																								mainPanel.add(btnMainJoin);
																								
																										JButton btnMainNew = new JButton("\uBC29\uC0DD\uC131");
																										
																										
																										btnMainNew.setBounds(600, 660, 160, 70);
																										btnMainNew.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
																										btnMainNew.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
																										mainPanel.add(btnMainNew);
																										
																										
																												JButton btnMainRefresh = new JButton("#");
																												
																												btnMainRefresh.setBounds(880, 30, 70, 70);
																												btnMainRefresh.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
																												btnMainRefresh.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
																												mainPanel.add(btnMainRefresh);
																												tblMain = new JTable(dtmMain);
																												tblMain.getTableHeader().setReorderingAllowed(false); //이동불가
																												tblMain.getTableHeader().setResizingAllowed(false); //크기조절불가
																												tblMain.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
																												
																														JScrollPane mainScroll = new JScrollPane(tblMain);
																														mainScroll.setBounds(30, 120, 920, 510);
																														mainPanel.add(mainScroll);
																														mainPanel.setVisible(false);
																														
																														
																														
																														
						
								JPanel infoPanel = new JPanel();
								
								infoPanel.setLayout(null);
								infoPanel.setBounds(0, 0, 982, 761);
								mainFrame.getContentPane().add(infoPanel);
								
										JButton btnInfoBack = new JButton("\u25C1");
										
										btnInfoBack.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
										btnInfoBack.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
										btnInfoBack.setBounds(30, 30, 100, 70);
										infoPanel.add(btnInfoBack);
										
												JLabel rblInfo = new JLabel("\uC815\uBCF4\uAD00\uB9AC");
												rblInfo.setHorizontalAlignment(SwingConstants.RIGHT);
												rblInfo.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 40));
												rblInfo.setBounds(350, 30, 600, 70);
												infoPanel.add(rblInfo);
												
														JLabel lblInfoNickname1 = new JLabel("\uB2C9\uB124\uC784");
														lblInfoNickname1.setHorizontalAlignment(SwingConstants.LEFT);
														lblInfoNickname1.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
														lblInfoNickname1.setBounds(30, 120, 200, 50);
														infoPanel.add(lblInfoNickname1);
														
																JLabel lblInfoNowPw = new JLabel("\uD604\uC7AC \uBE44\uBC00\uBC88\uD638");
																lblInfoNowPw.setHorizontalAlignment(SwingConstants.LEFT);
																lblInfoNowPw.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
																lblInfoNowPw.setBounds(30, 570, 200, 50);
																infoPanel.add(lblInfoNowPw);
																
																		JLabel lblInfoPw2 = new JLabel("\uBCC0\uACBD\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
																		lblInfoPw2.setHorizontalAlignment(SwingConstants.LEFT);
																		lblInfoPw2.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
																		lblInfoPw2.setBounds(30, 480, 250, 50);
																		infoPanel.add(lblInfoPw2);
																		
																				JLabel lblInfoId1 = new JLabel("\uC544\uC774\uB514");
																				lblInfoId1.setHorizontalAlignment(SwingConstants.LEFT);
																				lblInfoId1.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
																				lblInfoId1.setBounds(30, 210, 200, 50);
																				infoPanel.add(lblInfoId1);
																				
																						JLabel lblInfoPw1 = new JLabel("\uBCC0\uACBD \uBE44\uBC00\uBC88\uD638");
																						lblInfoPw1.setHorizontalAlignment(SwingConstants.LEFT);
																						lblInfoPw1.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
																						lblInfoPw1.setBounds(30, 390, 200, 50);
																						infoPanel.add(lblInfoPw1);
																						
																								JLabel lblInfoLog1 = new JLabel("\uC804\uC801");
																								lblInfoLog1.setHorizontalAlignment(SwingConstants.LEFT);
																								lblInfoLog1.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
																								lblInfoLog1.setBounds(30, 300, 200, 50);
																								infoPanel.add(lblInfoLog1);
																								
																										JLabel lblInfoNickname2 = new JLabel("\uB2C9\uB124\uC784");
																										lblInfoNickname2.setHorizontalAlignment(SwingConstants.LEFT);
																										lblInfoNickname2.setFont(new Font("넥슨Lv2고딕 Light", Font.PLAIN, 30));
																										lblInfoNickname2.setBounds(300, 120, 200, 50);
																										infoPanel.add(lblInfoNickname2);
																										
																												JLabel lblInfoId2 = new JLabel("\uC544\uC774\uB514");
																												lblInfoId2.setHorizontalAlignment(SwingConstants.LEFT);
																												lblInfoId2.setFont(new Font("넥슨Lv2고딕 Light", Font.PLAIN, 30));
																												lblInfoId2.setBounds(300, 210, 200, 50);
																												infoPanel.add(lblInfoId2);
																												
																														JLabel lblInfoLog2 = new JLabel("\uC2B9\uD328");
																														lblInfoLog2.setHorizontalAlignment(SwingConstants.LEFT);
																														lblInfoLog2.setFont(new Font("넥슨Lv2고딕 Light", Font.PLAIN, 30));
																														lblInfoLog2.setBounds(300, 300, 200, 50);
																														infoPanel.add(lblInfoLog2);
																														
																																txtInfoPw1 = new JPasswordField();
																																txtInfoPw1.setBounds(300, 390, 400, 50);
																																infoPanel.add(txtInfoPw1);
																																
																																		txtInfoPw2 = new JPasswordField();
																																		txtInfoPw2.setBounds(300, 480, 400, 50);
																																		infoPanel.add(txtInfoPw2);
																																		
																																				txtInfoNowPw = new JPasswordField();
																																				txtInfoNowPw.setBounds(300, 570, 400, 50);
																																				infoPanel.add(txtInfoNowPw);
																																				
																																						JButton btnInfoChange = new JButton("\uC218\uC815");
																																						
																																						btnInfoChange.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
																																						btnInfoChange.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
																																						btnInfoChange.setBounds(790, 670, 160, 70);
																																						infoPanel.add(btnInfoChange);
																								
																																						
																																						
																								dtmMain = new DefaultTableModel(content, header) {
																									public boolean isCellEditable(int rowIndex, int mCollIndex) {
																										return false;
																									}
																								};
																										
																										
																										
		
				JPanel loginPanel = new JPanel();
				loginPanel.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
				loginPanel.setBounds(0, 0, 982, 761);
				mainFrame.getContentPane().add(loginPanel);
				loginPanel.setLayout(null);
				
						JLabel lblTitle = new JLabel("<html><body style='text-align:center;'>Joker<br />GAME</body></html>");
						lblTitle.setBounds(250, 80, 500, 250);
						lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
						lblTitle.setFont(new Font("넥슨Lv1고딕 Bold", Font.PLAIN, 90));
						loginPanel.add(lblTitle);
						
								txtId = new JTextField();
								txtId.setBounds(250, 380, 500, 50);
								txtId.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
								loginPanel.add(txtId);
								txtId.setColumns(10);
								
										txtPw = new JTextField();
										txtPw.setBounds(250, 460, 500, 50);
										txtPw.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
										txtPw.setColumns(10);
										loginPanel.add(txtPw);
										
												JButton btnLoginFindPw = new JButton("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
												btnLoginFindPw.setBounds(250, 540, 200, 50);
												btnLoginFindPw.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
												btnLoginFindPw.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
												loginPanel.add(btnLoginFindPw);
												
														JButton btnLoginProc = new JButton("\uB85C\uADF8\uC778");
														btnLoginProc.setBounds(550, 540, 200, 50);
														btnLoginProc.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
														btnLoginProc.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
														loginPanel.add(btnLoginProc);
														
																JButton btnLoginJoin = new JButton("\uD68C\uC6D0\uAC00\uC785");
																btnLoginJoin.setBounds(400, 620, 200, 50);
																btnLoginJoin.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
																btnLoginJoin.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
																loginPanel.add(btnLoginJoin);
																loginPanel.setVisible(true); //최초시작 시 로그인panel만 활성화
																
																		
																		

		JPanel joinPanel = new JPanel();
		joinPanel.setBounds(0, 0, 982, 761);
		mainFrame.getContentPane().add(joinPanel);
		joinPanel.setLayout(null);

		JLabel lblJoinNick = new JLabel("\uB2C9\uB124\uC784");
		lblJoinNick.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
		lblJoinNick.setHorizontalAlignment(SwingConstants.LEFT);
		lblJoinNick.setBounds(100, 120, 200, 50);
		joinPanel.add(lblJoinNick);

		txtJoinNick = new JTextField();
		txtJoinNick.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
		txtJoinNick.setColumns(10);
		txtJoinNick.setBounds(350, 120, 550, 50);
		joinPanel.add(txtJoinNick);

		JLabel lblJoinPw = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblJoinPw.setHorizontalAlignment(SwingConstants.LEFT);
		lblJoinPw.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
		lblJoinPw.setBounds(100, 190, 200, 50);
		joinPanel.add(lblJoinPw);

		txtJoinPw = new JTextField();
		txtJoinPw.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
		txtJoinPw.setColumns(10);
		txtJoinPw.setBounds(350, 190, 550, 50);
		joinPanel.add(txtJoinPw);

		JLabel lblJoinPwChk = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
		lblJoinPwChk.setHorizontalAlignment(SwingConstants.LEFT);
		lblJoinPwChk.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
		lblJoinPwChk.setBounds(100, 260, 200, 50);
		joinPanel.add(lblJoinPwChk);

		txtJoinPwChk = new JTextField();
		txtJoinPwChk.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
		txtJoinPwChk.setColumns(10);
		txtJoinPwChk.setBounds(350, 260, 550, 50);
		joinPanel.add(txtJoinPwChk);

		JLabel lblJoinId = new JLabel("\uC544\uC774\uB514");
		lblJoinId.setHorizontalAlignment(SwingConstants.LEFT);
		lblJoinId.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
		lblJoinId.setBounds(100, 330, 200, 50);
		joinPanel.add(lblJoinId);

		txtJoinId = new JTextField();
		txtJoinId.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
		txtJoinId.setColumns(10);
		txtJoinId.setBounds(350, 330, 550, 50);
		joinPanel.add(txtJoinId);

		JButton btnJoinIdChk = new JButton("\uC911\uBCF5\uCCB4\uD06C");

		btnJoinIdChk.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
		btnJoinIdChk.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnJoinIdChk.setBounds(700, 400, 200, 50);
		joinPanel.add(btnJoinIdChk);

		JLabel lblJoinQ = new JLabel("\uBCF8\uC778\uD655\uC778 \uC9C8\uBB38");
		lblJoinQ.setHorizontalAlignment(SwingConstants.LEFT);
		lblJoinQ.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
		lblJoinQ.setBounds(100, 470, 200, 50);
		joinPanel.add(lblJoinQ);

		txtJoinQ = new JTextField();
		txtJoinQ.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
		txtJoinQ.setColumns(10);
		txtJoinQ.setBounds(349, 470, 550, 50);
		joinPanel.add(txtJoinQ);

		JLabel lblJoinA = new JLabel("\uBCF8\uC778\uD655\uC778 \uB2F5\uBCC0");
		lblJoinA.setHorizontalAlignment(SwingConstants.LEFT);
		lblJoinA.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
		lblJoinA.setBounds(100, 540, 200, 50);
		joinPanel.add(lblJoinA);

		txtJoinA = new JTextField();
		txtJoinA.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
		txtJoinA.setColumns(10);
		txtJoinA.setBounds(350, 540, 550, 50);
		joinPanel.add(txtJoinA);

		JButton btnJoinBack = new JButton("\u25C1");
		btnJoinBack.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
		btnJoinBack.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnJoinBack.setBounds(30, 30, 100, 70);
		joinPanel.add(btnJoinBack);

		JButton btnJoin = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnJoin.setEnabled(false);
		btnJoin.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
		btnJoin.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnJoin.setBounds(400, 650, 200, 50);
		joinPanel.add(btnJoin);

		JLabel lblJoinIdChk = new JLabel("");
		lblJoinIdChk.setHorizontalAlignment(SwingConstants.LEFT);
		lblJoinIdChk.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
		lblJoinIdChk.setBounds(350, 390, 320, 50);
		joinPanel.add(lblJoinIdChk);

		JPanel roomPanel = new JPanel();
		
		roomPanel.setLayout(null);
		roomPanel.setBounds(0, 0, 982, 761);
		mainFrame.getContentPane().add(roomPanel);

		JButton btnRoomName = new JButton("\uC774\uB984\uBCC0\uACBD");
		btnRoomName.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
		btnRoomName.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnRoomName.setBounds(30, 660, 160, 70);
		roomPanel.add(btnRoomName);

		JButton btnRoomKick = new JButton("\uAC15\uD1F4");
		btnRoomKick.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
		btnRoomKick.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnRoomKick.setBounds(410, 660, 160, 70);
		roomPanel.add(btnRoomKick);

		JButton btnRoomReady = new JButton("\uC900\uBE44\uC644\uB8CC");
		btnRoomReady.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
		btnRoomReady.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnRoomReady.setBounds(790, 660, 160, 70);
		roomPanel.add(btnRoomReady);

		JButton btnRoomBet = new JButton("\uBC30\uD305");
		btnRoomBet.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
		btnRoomBet.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnRoomBet.setBounds(600, 660, 160, 70);
		roomPanel.add(btnRoomBet);
		dtmRoom = new DefaultTableModel(roomContent, roomHeader){ //수정불가능하게 변경
			public boolean isCellEditable(int rowIndex, int mCollIndex) {
				return false;
			}
		};
		tblRoom = new JTable(dtmRoom);
		tblRoom.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));

		JScrollPane roomScroll = new JScrollPane(tblRoom);
		roomScroll.setBounds(30, 120, 920, 510);
		roomPanel.add(roomScroll);

		JButton btnRoomBack = new JButton("\u25C1");
		
		btnRoomBack.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
		btnRoomBack.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnRoomBack.setBounds(30, 30, 100, 70);
		roomPanel.add(btnRoomBack);

		JLabel rblRoom = new JLabel("\uB300\uAE30\uC2E4");
		rblRoom.setHorizontalAlignment(SwingConstants.RIGHT);
		rblRoom.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 40));
		rblRoom.setBounds(350, 30, 600, 70);
		roomPanel.add(rblRoom);
		
		txtRoomName = new JTextField();
		txtRoomName.setBounds(300, 50, 116, 21);
		roomPanel.add(txtRoomName);
		txtRoomName.setColumns(10);
		
		JButton btnRoomName_1 = new JButton("\uC0C8\uB85C\uACE0\uCE68");
		btnRoomName_1.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
		btnRoomName_1.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnRoomName_1.setBounds(220, 660, 160, 70);
		roomPanel.add(btnRoomName_1);

		JPanel friendPanel = new JPanel();
		friendPanel.setLayout(null);
		friendPanel.setBounds(0, 0, 982, 761);
		mainFrame.getContentPane().add(friendPanel);

		JButton btnMainFriend = new JButton("\uC218\uB77D");
		btnMainFriend.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
		btnMainFriend.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnMainFriend.setBounds(30, 660, 160, 70);
		friendPanel.add(btnMainFriend);

		JButton btnMainMatch_1 = new JButton("\uAC70\uC808");
		btnMainMatch_1.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
		btnMainMatch_1.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnMainMatch_1.setBounds(220, 660, 160, 70);
		friendPanel.add(btnMainMatch_1);

		JButton btnMainDel_1 = new JButton("\uCD94\uAC00");
		btnMainDel_1.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
		btnMainDel_1.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnMainDel_1.setBounds(410, 660, 160, 70);
		friendPanel.add(btnMainDel_1);

		JButton btnMainJoin_1 = new JButton("\uB530\uB77C\uAC00\uAE30");
		btnMainJoin_1.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
		btnMainJoin_1.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnMainJoin_1.setBounds(790, 660, 160, 70);
		friendPanel.add(btnMainJoin_1);

		JButton btnMainNew_1 = new JButton("\uC0AD\uC81C");
		btnMainNew_1.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
		btnMainNew_1.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnMainNew_1.setBounds(600, 660, 160, 70);
		friendPanel.add(btnMainNew_1);
		JTable tblInvite = new JTable(inviteContent, inviteHeader);
		tblInvite.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));

		JScrollPane inviteScroll = new JScrollPane(tblInvite);
		inviteScroll.setBounds(30, 120, 350, 510);
		friendPanel.add(inviteScroll);

		JButton btnFriendsBack = new JButton("\u25C1");
		btnFriendsBack.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
		btnFriendsBack.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnFriendsBack.setBounds(30, 30, 100, 70);
		friendPanel.add(btnFriendsBack);
		JTable tblFriend = new JTable(friendContent, friendHeader);
		tblFriend.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));

		JScrollPane friendScroll = new JScrollPane(tblFriend);
		friendScroll.setBounds(450, 120, 500, 510);
		friendPanel.add(friendScroll);

		JLabel rblInfo_1 = new JLabel("\uCE5C\uAD6C\uAD00\uB9AC");
		rblInfo_1.setHorizontalAlignment(SwingConstants.RIGHT);
		rblInfo_1.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 40));
		rblInfo_1.setBounds(350, 30, 600, 70);
		friendPanel.add(rblInfo_1);

		JPanel rankPanel = new JPanel();
		
		rankPanel.setBounds(0, 0, 982, 761);
		mainFrame.getContentPane().add(rankPanel);
		rankPanel.setLayout(null);

		JButton btnRankBack = new JButton("\u25C1");
		btnRankBack.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
		btnRankBack.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnRankBack.setBounds(30, 30, 100, 70);
		rankPanel.add(btnRankBack);
		dtmRank = new DefaultTableModel(rankContent, rankHeader) {
			public boolean isCellEditable(int rowIndex, int mCollIndex) {
			return false;
			}
			};
		tblRank = new JTable(dtmRank);
		tblRank.getTableHeader().setReorderingAllowed(false); //이동불가
		tblRank.getTableHeader().setResizingAllowed(false); //크기조절불가
		tblRank.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));infoPanel.setVisible(false);
		
		tblRank.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));

		JScrollPane rankScrollPane = new JScrollPane(tblRank);
		rankScrollPane.setBounds(30, 120, 920, 510);
		rankPanel.add(rankScrollPane);

		JLabel rblRank = new JLabel("\uB7AD\uD0B9");
		rblRank.setHorizontalAlignment(SwingConstants.RIGHT);
		rblRank.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 40));
		rblRank.setBounds(350, 30, 600, 70);
		rankPanel.add(rblRank);

		JPanel findPwPanel = new JPanel();
		findPwPanel.setBounds(0, 0, 982, 761);
		mainFrame.getContentPane().add(findPwPanel);
		findPwPanel.setLayout(null);

		JButton btnFindPwBack = new JButton("\u25C1");
		btnFindPwBack.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
		btnFindPwBack.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnFindPwBack.setBounds(30, 30, 100, 70);
		findPwPanel.add(btnFindPwBack);

		JLabel lblFindPwId = new JLabel("\uC544\uC774\uB514");
		lblFindPwId.setHorizontalAlignment(SwingConstants.LEFT);
		lblFindPwId.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
		lblFindPwId.setBounds(100, 200, 200, 50);
		findPwPanel.add(lblFindPwId);

		JLabel lblFindPwQ = new JLabel("\uBCF8\uC778\uD655\uC778 \uC9C8\uBB38");
		lblFindPwQ.setHorizontalAlignment(SwingConstants.LEFT);
		lblFindPwQ.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
		lblFindPwQ.setBounds(100, 350, 200, 50);
		findPwPanel.add(lblFindPwQ);

		JLabel lblFindPwA = new JLabel("\uBCF8\uC778\uD655\uC778 \uB2F5\uBCC0");
		lblFindPwA.setHorizontalAlignment(SwingConstants.LEFT);
		lblFindPwA.setFont(new Font("넥슨Lv2고딕 Bold", Font.PLAIN, 30));
		lblFindPwA.setBounds(100, 500, 200, 50);
		findPwPanel.add(lblFindPwA);

		txtFindPwId = new JTextField();
		txtFindPwId.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
		txtFindPwId.setColumns(10);
		txtFindPwId.setBounds(350, 200, 550, 50);
		findPwPanel.add(txtFindPwId);

		txtFindPwQ = new JTextField();
		txtFindPwQ.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
		txtFindPwQ.setColumns(10);
		txtFindPwQ.setBounds(350, 350, 550, 50);
		findPwPanel.add(txtFindPwQ);

		txtFindPwA = new JTextField();
		txtFindPwA.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
		txtFindPwA.setColumns(10);
		txtFindPwA.setBounds(350, 500, 550, 50);
		findPwPanel.add(txtFindPwA);

		JButton btnFindPwProc = new JButton("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		btnFindPwProc.setFont(new Font("넥슨Lv2고딕", Font.PLAIN, 20));
		btnFindPwProc.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnFindPwProc.setBounds(700, 650, 200, 50);
		findPwPanel.add(btnFindPwProc);

		joinPanel.setVisible(false);
		findPwPanel.setVisible(false);
		friendPanel.setVisible(false);
		rankPanel.setVisible(false);
		roomPanel.setVisible(false);
		
		// events
		
		
		//랭킹 페이지
		
		
		//랭킹 페이지가 보여질 때
		rankPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				//랭킹 정리해서 tblRank에 추가
				
				//1.랭커 100명의 arraylist 가져오기
				ArrayList<MemberDTO> list = memberDAO.getRanker();
				
				//2. 필요한 내용으로 2차원배열 만들기
				String rankers[][] = new String[list.size()][4];
				int cnt = 0;
				for(MemberDTO i : list) {
					rankers[cnt][0] = String.valueOf(cnt); //랭크
					rankers[cnt][1] = i.getNickname(); //닉네임
					rankers[cnt][2] = String.valueOf(i.getExp() / 100);
					rankers[cnt][3] = (i.getWin() + "승 " + i.getLose() + "패"); //승패
				}
				
				//tblRank에 값 추가
				dtmRank = (DefaultTableModel)tblMain.getModel();
				dtmRank.setNumRows(0);
				dtmRank = new DefaultTableModel(rankers, rankHeader){ //수정 불가능한 테이블로
					public boolean isCellEditable(int rowIndex, int mCollIndex) {
						return false;
					}
				};
				tblMain.setModel(dtmRank);
			}
		});
		
		
		//메인 페이지
		
		
		//메인 페이지가 보여질 때
		mainPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				//mainPanel 초기화
				try {
					memberDTO = memberDAO.getMember(memberDTO.getMemberId());//memberDTO 갱신
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} 
				
				//lblMain 초기화
				StringBuffer msg = new StringBuffer();
				msg.append("Lv" + memberDTO.getExp() / 100);
				msg.append(" ");
				msg.append(memberDTO.getNickname() + "님 반갑습니다");
				lblMain.setText(msg.toString());
				
				//tblMain 초기화
				//현재 대기중인 방 목록 가져와서 출력하기
				String[][] rooms;
				try {
					ArrayList<ArrayList<String>> list = roomDAO.getRooms(0);
					//list를 2차원 배열로 변환
					
					if(list == null) {
						rooms = new String[0][0];
					}else {
						rooms = new String[list.size()][list.get(0).size()];
						int i = 0;
						int j = 0;
						for(ArrayList<String> a : list) {
							for(String b : a) {
								rooms[i][j++] = b;
							}
							i++;
						}
					}
					//tblMain에 값 추가
					dtmMain = (DefaultTableModel)tblMain.getModel();
					dtmMain.setNumRows(0);
					dtmMain = new DefaultTableModel(rooms, header){ //수정 불가능한 테이블로
						public boolean isCellEditable(int rowIndex, int mCollIndex) {
							return false;
						}
					};
					tblMain.setModel(dtmMain);
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		//방 생성 버튼 눌렀을 때
		btnMainNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//1. 방 생성
				try {
					if(roomDAO.newRoom(memberDTO.getMemberId())) {
						//방 생성 성공 시 내 방 정보 가져온 뒤 방에 입장
						roomDTO = new RoomDTO();
						roomDTO.setRoomId(roomDAO.getMyRoom(memberDTO.getMemberId()));
						roomDTO = roomDAO.getRoom(roomDTO.getRoomId());
						
						mainPanel.setVisible(false);
						roomPanel.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "방 생성 실패");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//방 입장 눌렀을 때
		btnMainJoin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//선택된 방 번호 가져오기
				int col = tblMain.getSelectedColumn();
				int row = 0;
				String temp = String.valueOf(tblMain.getValueAt(row, col));
				int roomId = Integer.parseInt(temp);
				try {
					//방 입장 수행
					if(roomDAO.joinMyRoom(memberDTO.getMemberId(), row)) {
						//입장에 성공한 경우 페이지 roomPanel으로 이동시킴
						roomDTO = roomDAO.getRoom(roomId); //이동할 방의 정보를 미리 가져옴
						mainPanel.setVisible(false);
						roomPanel.setVisible(true);
					}else {
						//입장에 실패한 경우
						JOptionPane.showMessageDialog(null, "가득 찼습니다. 새로고침 해 주세요");
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		//회원정보 버튼 눌렀을 때
		btnMainInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainPanel.setVisible(false); //현재 페이지를 비활성화
				infoPanel.setVisible(true); //회원정보 페이지를 활성화
			}
		});
		
		//친구 버튼 눌렀을 때
		btnMainFriends.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainPanel.setVisible(false); //현재 페이지를 비활성화
				friendPanel.setVisible(true); //친구 페이지를 활성화
			}
		});
		
		//랭킹 버튼 눌렀을 때
		btnMainRank.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainPanel.setVisible(false); //현재 페이지를 비활성화
				rankPanel.setVisible(true); //친구 페이지를 활성화
			}
		});
		
		//랜덤매칭 버튼 눌렀을 때
		btnMainMatch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		//회원정보 페이지
		
		//회원정보 페이지가 보여질 때
		infoPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				try {
					memberDTO = memberDAO.getMember(memberDTO.getMemberId()); //memberDTO를 갱신
					
					//내용 초기화
					lblInfoNickname2.setText(memberDTO.getNickname());
					lblInfoId2.setText(memberDTO.getMemberId());
					lblInfoLog2.setText(memberDTO.getMatchCount() + "전 " + memberDTO.getWin() + "승 " + memberDTO.getLose() + "패");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//회원정보 수정 버튼을 클릭했을 때
		btnInfoChange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!txtInfoPw1.getText().equals(txtInfoPw2.getText())) {
					//입력한 비밀번호들이 다르면
					JOptionPane.showMessageDialog(null, "새 비밀번호가 다릅니다");
				}else { //입력한 새 비밀번호가 같으면
					try {
						if(!memberDAO.login(memberDTO.getMemberId(), txtInfoNowPw.getText())) {
							//입력한 현재 비밀번호가 같으면
							//DB에 저장된 비밀번호 수정
							if(memberDAO.updatePw(memberDTO.getMemberId(), txtInfoPw1.getText())) {
								JOptionPane.showMessageDialog(null, "수정완료");
							}else {
								JOptionPane.showMessageDialog(null, "수정실패");
							}
							txtInfoNowPw.setText("");
							txtInfoPw1.setText("");
							txtInfoPw2.setText("");
							infoPanel.setVisible(false);
							mainPanel.setVisible(true); //mainPanel로 이동
						}else { //입력한 현재 비밀번호가 다르면
							JOptionPane.showMessageDialog(null, "현재 비밀번호가 틀립니다");
							txtInfoNowPw.setText(""); //txtInfoNowPw 비우기
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		//뒤로가기 버튼을 클릭했을 때
		btnInfoBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtInfoNowPw.setText("");
				txtInfoPw1.setText("");
				txtInfoPw2.setText("");
				infoPanel.setVisible(false);
				mainPanel.setVisible(true); //싹 초기화 후 mainPanel로 이동
			}
		});
		
		//게임방 페이지
		
		//게임방 페이지가 보여질 때
		roomPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				//1. 방 입장
				try {
					if(roomDAO.joinMyRoom(memberDTO.getMemberId(), roomDTO.getRoomId())) {
						//1. 방장인 경우 방 이름 수정 활성화, 강퇴 버튼 활성화
						if(roomDTO.getRoomId() == roomDAO.getMyRoom(memberDTO.getMemberId())) {
							txtRoomName.setEditable(true);
							btnRoomKick.setVisible(true);
						}else {
							//2. 아닌 경우 비활성화
							txtRoomName.setEditable(false);
							btnRoomKick.setVisible(false);
						}
						//들어와 있는 사람들 가져오기
						ArrayList<ArrayList<String>> list = roomDAO.getMembers(roomDTO.getRoomId());
						String members[][];
						
						//2차원 배열로 변환
						if(list != null) {
							members = new String[list.size()][list.get(0).size()];
							int i = 0;
							int j = 0;
							for(ArrayList<String> a : list) {
								for(String b : a) {
									members[i][j++] = b;
								}
								i++;
							}
						}else {
							members = new String[0][0];
						}
						
						//tblRoom에 값 추가
						dtmRoom = (DefaultTableModel)tblRoom.getModel();
						dtmRoom.setNumRows(0);
						dtmRoom = new DefaultTableModel(members, roomHeader){ //수정 불가능한 테이블로
							public boolean isCellEditable(int rowIndex, int mCollIndex) {
								return false;
							}
						};
						tblMain.setModel(dtmMain);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//새로고침 버튼을 눌렀을 때
		btnMainRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					//들어와 있는 사람들 가져오기
					ArrayList<ArrayList<String>> list = roomDAO.getMembers(roomDTO.getRoomId());
					String members[][];
					
					//2차원 배열로 변환
					if(list != null) {
						members = new String[list.size()][list.get(0).size()];
						int i = 0;
						int j = 0;
						for(ArrayList<String> a : list) {
							for(String b : a) {
								members[i][j++] = b;
							}
							i++;
						}
					}else {
						members = new String[0][0];
					}
					
					//tblRoom에 값 추가
					dtmRoom = (DefaultTableModel)tblRoom.getModel();
					dtmRoom.setNumRows(0);
					dtmRoom = new DefaultTableModel(members, roomHeader){ //수정 불가능한 테이블로
						public boolean isCellEditable(int rowIndex, int mCollIndex) {
							return false;
						}
					};
					tblMain.setModel(dtmMain);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//방 나가기를 눌렀을 때
		btnRoomBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//방 나가기 수행
				try {
					roomDAO.roomOut(memberDTO.getMemberId(), roomDTO.getRoomId());
					//수행 후 페이지 이동
					roomPanel.setVisible(false);
					mainPanel.setVisible(true);
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		
		// 로그인 페이지
		
		// 회원가입 버튼 클릭
		btnLoginJoin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loginPanel.setVisible(false); //로그인 페이지 비활성화
				joinPanel.setVisible(true); //회원가입 페이지 활성화
			}
		});
		
		// 로그인 버튼 클릭
		btnLoginProc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id = txtId.getText();
				String pw = txtPw.getText();
				try {
					if(memberDAO.login(id, pw)) {
						//id와 pw비교 결과가 같으면 메인 페이지로
						loginPanel.setVisible(false); //로그인페이지 비활성화
						memberDTO = memberDAO.getMember(id); //멤버 정보 저장
						memberDAO.setState(id, 1); //멤버 상태를 1(로그인)으로 변경
						
						mainPanel.setVisible(true); //메인페이지 활성화
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// 회원가입 페이지

		// 아이디 중복체크 버튼 클릭
		btnJoinIdChk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id = txtJoinId.getText();
				try {
					if (memberDAO.idCheck(id)) {
						txtJoinId.setEnabled(false); // 아이디 텍스트필드 비활성화
						btnJoinIdChk.setEnabled(false); // 중복체크 버튼 비활성화
						btnJoin.setEnabled(true); // 회원가입 버튼 활성화
						lblJoinIdChk.setText("가입가능!"); // 안내라벨 변경
					} else {
						lblJoinIdChk.setText("이미 존재하는 아이디예요"); // 안내라벨 변경
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("아이디 중복체크 에러");
				}
			}
		});
		// 뒤로가기 버튼 클릭
		btnJoinBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					txtJoinId.setEnabled(true);// 아이디 텍스트필드 활성화
					btnJoinIdChk.setEnabled(true);// 중복체크 버튼 활성화
					btnJoin.setEnabled(false);// 회원가입 버튼 활성화
					lblJoinIdChk.setText(""); // 안내라벨 초기화
					loginPanel.setVisible(true); //로그인 페이지 활성화
					joinPanel.setVisible(false); //회원가입 페이지 비활성화

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("회원가입 페이지 뒤로가기 에러");
				}
			}
		});
		// 회원가입 버튼 클릭
		btnJoin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 비밀번호 같은지 체크
				if (txtJoinPwChk.getText().equals(txtJoinPw.getText())) {
					MemberDTO mDTO = new MemberDTO(); // MemberDTO 객체 생성 및 초기화
					memberDTO.setMemberId(txtJoinId.getText());
					memberDTO.setMemberPw(txtJoinPw.getText());
					memberDTO.setNickname(txtJoinNick.getText());
					memberDTO.setAnswer(txtJoinA.getText());
					memberDTO.setQuestion(txtJoinQ.getText());
					try {
						if (memberDAO.join(mDTO)) {
							txtJoinId.setEnabled(true);// 아이디 텍스트필드 활성화
							btnJoinIdChk.setEnabled(true);// 중복체크 버튼 활성화
							btnJoin.setEnabled(false);// 회원가입 버튼 활성화
							lblJoinIdChk.setText(""); // 안내라벨 초기화
						} else {
							JOptionPane.showMessageDialog(null, "가입 실패"); //가입실패 알림창 띄우기
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println("회원가입 에러");
						JOptionPane.showMessageDialog(null, "가입 실패"); //가입실패 알림창 띄우기
					}
				}else {
					JOptionPane.showMessageDialog(null, "비밀번호를 확인해주세요");
				}
			}
		});
	}
}
