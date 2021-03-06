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

import com.jokergame.friends.FriendsDAO;
import com.jokergame.friends.FriendsDTO;
import com.jokergame.friends.ReqDAO;
import com.jokergame.friends.ReqDTO;
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
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Main {
	MemberDAO memberDAO = MemberDAO.getInstance();
	MemberDTO memberDTO;
	
	RoomDAO roomDAO = RoomDAO.getInstance();
	RoomDTO roomDTO;
	
	FriendsDAO friendsDAO = FriendsDAO.getInstance();
	FriendsDTO friendsDTO;
	
	ReqDAO reqDAO = ReqDAO.getInstance();
	ReqDTO reqDTO;

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
	private JTable tblFriends;
	private DefaultTableModel dtmFriends;
	private JTable tblInvite;
	private DefaultTableModel dtmInvite;
	private JTextField txtRoomName;
	private JTextField txtFriendId;

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
		String roomHeader[] = { "????", "??????", "??????????", "????" };

		String inviteContent[][] = {};
		String inviteHeader[] = { "????????" };

		String friendContent[][] = {};
		String friendHeader[] = { "??????", "????" };

		String rankHeader[] = { "????", "??????", "????", "????" };
		String rankContent[][] = {};

		String header[] = { "??????", "??????", "????", "??????" };
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
												lblMain.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 40));
												mainPanel.add(lblMain);
												
														JButton btnMainInfo = new JButton("\uD68C\uC6D0\uC815\uBCF4");
														
														btnMainInfo.setBounds(730, 30, 140, 70);
														btnMainInfo.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
														btnMainInfo.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
														mainPanel.add(btnMainInfo);
														
																JButton btnMainRank = new JButton("\uB7AD\uD0B9");
																
																btnMainRank.setBounds(580, 30, 140, 70);
																btnMainRank.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
																btnMainRank.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
																mainPanel.add(btnMainRank);
																
																		JButton btnMainFriends = new JButton("\uCE5C\uAD6C");
																		
																		btnMainFriends.setBounds(30, 660, 160, 70);
																		btnMainFriends.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
																		btnMainFriends.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
																		mainPanel.add(btnMainFriends);
																		
																				JButton btnMainMatch = new JButton("\uB79C\uB364\uB9E4\uCE6D");
																				
																				btnMainMatch.setBounds(220, 660, 160, 70);
																				btnMainMatch.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
																				btnMainMatch.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
																				mainPanel.add(btnMainMatch);
																				
																						JButton btnMainDel = new JButton("\uBC29\uC0AD\uC81C");
																						btnMainDel.setBounds(410, 660, 160, 70);
																						btnMainDel.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
																						btnMainDel.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
																						mainPanel.add(btnMainDel);
																						
																								JButton btnMainJoin = new JButton("\uB4E4\uC5B4\uAC00\uAE30");
																								
																								btnMainJoin.setBounds(790, 660, 160, 70);
																								btnMainJoin.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
																								btnMainJoin.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
																								mainPanel.add(btnMainJoin);
																								
																										JButton btnMainNew = new JButton("\uBC29\uC0DD\uC131");
																										
																										
																										btnMainNew.setBounds(600, 660, 160, 70);
																										btnMainNew.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
																										btnMainNew.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
																										mainPanel.add(btnMainNew);
																										
																										
																												JButton btnMainRefresh = new JButton("#");
																												
																												
																												btnMainRefresh.setBounds(880, 30, 70, 70);
																												btnMainRefresh.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
																												btnMainRefresh.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
																												mainPanel.add(btnMainRefresh);
																												tblMain = new JTable(dtmMain);
																												tblMain.getTableHeader().setReorderingAllowed(false); //????????
																												tblMain.getTableHeader().setResizingAllowed(false); //????????????
																												tblMain.setFont(new Font("????Lv2????", Font.PLAIN, 20));
																												
																														JScrollPane mainScroll = new JScrollPane(tblMain);
																														mainScroll.setBounds(30, 120, 920, 510);
																														mainPanel.add(mainScroll);
																														mainPanel.setVisible(false);
																														
																														
																														
																														
						
								JPanel infoPanel = new JPanel();
								
								infoPanel.setLayout(null);
								infoPanel.setBounds(0, 0, 982, 761);
								mainFrame.getContentPane().add(infoPanel);
								
										JButton btnInfoBack = new JButton("\u25C1");
										
										btnInfoBack.setFont(new Font("????Lv2????", Font.PLAIN, 20));
										btnInfoBack.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
										btnInfoBack.setBounds(30, 30, 100, 70);
										infoPanel.add(btnInfoBack);
										
												JLabel rblInfo = new JLabel("\uC815\uBCF4\uAD00\uB9AC");
												rblInfo.setHorizontalAlignment(SwingConstants.RIGHT);
												rblInfo.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 40));
												rblInfo.setBounds(350, 30, 600, 70);
												infoPanel.add(rblInfo);
												
														JLabel lblInfoNickname1 = new JLabel("\uB2C9\uB124\uC784");
														lblInfoNickname1.setHorizontalAlignment(SwingConstants.LEFT);
														lblInfoNickname1.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
														lblInfoNickname1.setBounds(30, 120, 200, 50);
														infoPanel.add(lblInfoNickname1);
														
																JLabel lblInfoNowPw = new JLabel("\uD604\uC7AC \uBE44\uBC00\uBC88\uD638");
																lblInfoNowPw.setHorizontalAlignment(SwingConstants.LEFT);
																lblInfoNowPw.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
																lblInfoNowPw.setBounds(30, 570, 200, 50);
																infoPanel.add(lblInfoNowPw);
																
																		JLabel lblInfoPw2 = new JLabel("\uBCC0\uACBD\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
																		lblInfoPw2.setHorizontalAlignment(SwingConstants.LEFT);
																		lblInfoPw2.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
																		lblInfoPw2.setBounds(30, 480, 250, 50);
																		infoPanel.add(lblInfoPw2);
																		
																				JLabel lblInfoId1 = new JLabel("\uC544\uC774\uB514");
																				lblInfoId1.setHorizontalAlignment(SwingConstants.LEFT);
																				lblInfoId1.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
																				lblInfoId1.setBounds(30, 210, 200, 50);
																				infoPanel.add(lblInfoId1);
																				
																						JLabel lblInfoPw1 = new JLabel("\uBCC0\uACBD \uBE44\uBC00\uBC88\uD638");
																						lblInfoPw1.setHorizontalAlignment(SwingConstants.LEFT);
																						lblInfoPw1.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
																						lblInfoPw1.setBounds(30, 390, 200, 50);
																						infoPanel.add(lblInfoPw1);
																						
																								JLabel lblInfoLog1 = new JLabel("\uC804\uC801");
																								lblInfoLog1.setHorizontalAlignment(SwingConstants.LEFT);
																								lblInfoLog1.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
																								lblInfoLog1.setBounds(30, 300, 200, 50);
																								infoPanel.add(lblInfoLog1);
																								
																										JLabel lblInfoNickname2 = new JLabel("\uB2C9\uB124\uC784");
																										lblInfoNickname2.setHorizontalAlignment(SwingConstants.LEFT);
																										lblInfoNickname2.setFont(new Font("????Lv2???? Light", Font.PLAIN, 30));
																										lblInfoNickname2.setBounds(300, 120, 200, 50);
																										infoPanel.add(lblInfoNickname2);
																										
																												JLabel lblInfoId2 = new JLabel("\uC544\uC774\uB514");
																												lblInfoId2.setHorizontalAlignment(SwingConstants.LEFT);
																												lblInfoId2.setFont(new Font("????Lv2???? Light", Font.PLAIN, 30));
																												lblInfoId2.setBounds(300, 210, 200, 50);
																												infoPanel.add(lblInfoId2);
																												
																														JLabel lblInfoLog2 = new JLabel("\uC2B9\uD328");
																														lblInfoLog2.setHorizontalAlignment(SwingConstants.LEFT);
																														lblInfoLog2.setFont(new Font("????Lv2???? Light", Font.PLAIN, 30));
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
																																						
																																						btnInfoChange.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
																																						btnInfoChange.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
																																						btnInfoChange.setBounds(790, 670, 160, 70);
																																						infoPanel.add(btnInfoChange);
																								
																																						
																																						
																								dtmMain = new DefaultTableModel(content, header) {
																									public boolean isCellEditable(int rowIndex, int mCollIndex) {
																										return false;
																									}
																								};
																										
																										
																										
		
				JPanel loginPanel = new JPanel();
				loginPanel.setFont(new Font("????Lv2????", Font.PLAIN, 20));
				loginPanel.setBounds(0, 0, 982, 761);
				mainFrame.getContentPane().add(loginPanel);
				loginPanel.setLayout(null);
				
						JLabel lblTitle = new JLabel("<html><body style='text-align:center;'>Joker<br />GAME</body></html>");
						lblTitle.setBounds(250, 80, 500, 250);
						lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
						lblTitle.setFont(new Font("????Lv1???? Bold", Font.PLAIN, 90));
						loginPanel.add(lblTitle);
						
								txtId = new JTextField();
								txtId.setBounds(250, 380, 500, 50);
								txtId.setFont(new Font("????Lv2????", Font.PLAIN, 20));
								loginPanel.add(txtId);
								txtId.setColumns(10);
								
										txtPw = new JTextField();
										txtPw.setBounds(250, 460, 500, 50);
										txtPw.setFont(new Font("????Lv2????", Font.PLAIN, 20));
										txtPw.setColumns(10);
										loginPanel.add(txtPw);
										
												JButton btnLoginFindPw = new JButton("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
												btnLoginFindPw.setBounds(250, 540, 200, 50);
												btnLoginFindPw.setFont(new Font("????Lv2????", Font.PLAIN, 20));
												btnLoginFindPw.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
												loginPanel.add(btnLoginFindPw);
												
														JButton btnLoginProc = new JButton("\uB85C\uADF8\uC778");
														btnLoginProc.setBounds(550, 540, 200, 50);
														btnLoginProc.setFont(new Font("????Lv2????", Font.PLAIN, 20));
														btnLoginProc.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
														loginPanel.add(btnLoginProc);
														
																JButton btnLoginJoin = new JButton("\uD68C\uC6D0\uAC00\uC785");
																btnLoginJoin.setBounds(400, 620, 200, 50);
																btnLoginJoin.setFont(new Font("????Lv2????", Font.PLAIN, 20));
																btnLoginJoin.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
																loginPanel.add(btnLoginJoin);
																loginPanel.setVisible(true); //???????? ?? ??????panel?? ??????
																
																		
																		

		JPanel joinPanel = new JPanel();
		joinPanel.setBounds(0, 0, 982, 761);
		mainFrame.getContentPane().add(joinPanel);
		joinPanel.setLayout(null);

		JLabel lblJoinNick = new JLabel("\uB2C9\uB124\uC784");
		lblJoinNick.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
		lblJoinNick.setHorizontalAlignment(SwingConstants.LEFT);
		lblJoinNick.setBounds(100, 120, 200, 50);
		joinPanel.add(lblJoinNick);

		txtJoinNick = new JTextField();
		txtJoinNick.setFont(new Font("????Lv2????", Font.PLAIN, 20));
		txtJoinNick.setColumns(10);
		txtJoinNick.setBounds(350, 120, 550, 50);
		joinPanel.add(txtJoinNick);

		JLabel lblJoinPw = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblJoinPw.setHorizontalAlignment(SwingConstants.LEFT);
		lblJoinPw.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
		lblJoinPw.setBounds(100, 190, 200, 50);
		joinPanel.add(lblJoinPw);

		txtJoinPw = new JTextField();
		txtJoinPw.setFont(new Font("????Lv2????", Font.PLAIN, 20));
		txtJoinPw.setColumns(10);
		txtJoinPw.setBounds(350, 190, 550, 50);
		joinPanel.add(txtJoinPw);

		JLabel lblJoinPwChk = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
		lblJoinPwChk.setHorizontalAlignment(SwingConstants.LEFT);
		lblJoinPwChk.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
		lblJoinPwChk.setBounds(100, 260, 200, 50);
		joinPanel.add(lblJoinPwChk);

		txtJoinPwChk = new JTextField();
		txtJoinPwChk.setFont(new Font("????Lv2????", Font.PLAIN, 20));
		txtJoinPwChk.setColumns(10);
		txtJoinPwChk.setBounds(350, 260, 550, 50);
		joinPanel.add(txtJoinPwChk);

		JLabel lblJoinId = new JLabel("\uC544\uC774\uB514");
		lblJoinId.setHorizontalAlignment(SwingConstants.LEFT);
		lblJoinId.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
		lblJoinId.setBounds(100, 330, 200, 50);
		joinPanel.add(lblJoinId);

		txtJoinId = new JTextField();
		txtJoinId.setFont(new Font("????Lv2????", Font.PLAIN, 20));
		txtJoinId.setColumns(10);
		txtJoinId.setBounds(350, 330, 550, 50);
		joinPanel.add(txtJoinId);

		JButton btnJoinIdChk = new JButton("\uC911\uBCF5\uCCB4\uD06C");

		btnJoinIdChk.setFont(new Font("????Lv2????", Font.PLAIN, 20));
		btnJoinIdChk.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnJoinIdChk.setBounds(700, 400, 200, 50);
		joinPanel.add(btnJoinIdChk);

		JLabel lblJoinQ = new JLabel("\uBCF8\uC778\uD655\uC778 \uC9C8\uBB38");
		lblJoinQ.setHorizontalAlignment(SwingConstants.LEFT);
		lblJoinQ.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
		lblJoinQ.setBounds(100, 470, 200, 50);
		joinPanel.add(lblJoinQ);

		txtJoinQ = new JTextField();
		txtJoinQ.setFont(new Font("????Lv2????", Font.PLAIN, 20));
		txtJoinQ.setColumns(10);
		txtJoinQ.setBounds(349, 470, 550, 50);
		joinPanel.add(txtJoinQ);

		JLabel lblJoinA = new JLabel("\uBCF8\uC778\uD655\uC778 \uB2F5\uBCC0");
		lblJoinA.setHorizontalAlignment(SwingConstants.LEFT);
		lblJoinA.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
		lblJoinA.setBounds(100, 540, 200, 50);
		joinPanel.add(lblJoinA);

		txtJoinA = new JTextField();
		txtJoinA.setFont(new Font("????Lv2????", Font.PLAIN, 20));
		txtJoinA.setColumns(10);
		txtJoinA.setBounds(350, 540, 550, 50);
		joinPanel.add(txtJoinA);

		JButton btnJoinBack = new JButton("\u25C1");
		btnJoinBack.setFont(new Font("????Lv2????", Font.PLAIN, 20));
		btnJoinBack.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnJoinBack.setBounds(30, 30, 100, 70);
		joinPanel.add(btnJoinBack);

		JButton btnJoin = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnJoin.setEnabled(false);
		btnJoin.setFont(new Font("????Lv2????", Font.PLAIN, 20));
		btnJoin.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnJoin.setBounds(400, 650, 200, 50);
		joinPanel.add(btnJoin);

		JLabel lblJoinIdChk = new JLabel("");
		lblJoinIdChk.setHorizontalAlignment(SwingConstants.LEFT);
		lblJoinIdChk.setFont(new Font("????Lv2????", Font.PLAIN, 20));
		lblJoinIdChk.setBounds(350, 390, 320, 50);
		joinPanel.add(lblJoinIdChk);

		JPanel roomPanel = new JPanel();
		
		roomPanel.setLayout(null);
		roomPanel.setBounds(0, 0, 982, 761);
		mainFrame.getContentPane().add(roomPanel);

		JButton btnRoomName = new JButton("\uC774\uB984\uBCC0\uACBD");
		
		btnRoomName.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
		btnRoomName.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnRoomName.setBounds(30, 660, 160, 70);
		roomPanel.add(btnRoomName);

		JButton btnRoomKick = new JButton("\uAC15\uD1F4");
		btnRoomKick.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
		btnRoomKick.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnRoomKick.setBounds(410, 660, 160, 70);
		roomPanel.add(btnRoomKick);

		JButton btnRoomReady = new JButton("\uC900\uBE44\uC644\uB8CC");
		
		btnRoomReady.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
		btnRoomReady.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnRoomReady.setBounds(790, 660, 160, 70);
		roomPanel.add(btnRoomReady);

		JButton btnRoomBet = new JButton("\uBC30\uD305");
		
		btnRoomBet.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
		btnRoomBet.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnRoomBet.setBounds(600, 660, 160, 70);
		roomPanel.add(btnRoomBet);
		dtmRoom = new DefaultTableModel(roomContent, roomHeader){ //?????????????? ????
			public boolean isCellEditable(int rowIndex, int mCollIndex) {
				return false;
			}
		};
		tblRoom = new JTable(dtmRoom);
		tblRoom.setFont(new Font("????Lv2????", Font.PLAIN, 20));

		JScrollPane roomScroll = new JScrollPane(tblRoom);
		roomScroll.setBounds(30, 120, 920, 510);
		roomPanel.add(roomScroll);

		JButton btnRoomBack = new JButton("\u25C1");
		
		btnRoomBack.setFont(new Font("????Lv2????", Font.PLAIN, 20));
		btnRoomBack.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnRoomBack.setBounds(30, 30, 100, 70);
		roomPanel.add(btnRoomBack);

		JLabel rblRoom = new JLabel("\uB300\uAE30\uC2E4");
		rblRoom.setHorizontalAlignment(SwingConstants.RIGHT);
		rblRoom.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 40));
		rblRoom.setBounds(350, 30, 600, 70);
		roomPanel.add(rblRoom);
		
		txtRoomName = new JTextField();
		txtRoomName.setBounds(300, 50, 116, 21);
		roomPanel.add(txtRoomName);
		txtRoomName.setColumns(10);
		
		JButton btnRoomRefresh = new JButton("\uC0C8\uB85C\uACE0\uCE68");
		
		btnRoomRefresh.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
		btnRoomRefresh.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnRoomRefresh.setBounds(220, 660, 160, 70);
		roomPanel.add(btnRoomRefresh);

		JPanel friendPanel = new JPanel();
		
		friendPanel.setLayout(null);
		friendPanel.setBounds(0, 0, 982, 761);
		mainFrame.getContentPane().add(friendPanel);

		JButton btnFriendSure = new JButton("\uC218\uB77D");
		btnFriendSure.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
		btnFriendSure.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnFriendSure.setBounds(30, 660, 160, 70);
		friendPanel.add(btnFriendSure);

		JButton btnFriendIgnore = new JButton("\uAC70\uC808");
		
		btnFriendIgnore.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
		btnFriendIgnore.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnFriendIgnore.setBounds(220, 660, 160, 70);
		friendPanel.add(btnFriendIgnore);

		JButton btnFriendAdd = new JButton("\uCD94\uAC00");
		
		btnFriendAdd.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
		btnFriendAdd.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnFriendAdd.setBounds(410, 660, 160, 70);
		friendPanel.add(btnFriendAdd);

		JButton btnFriendJoin = new JButton("\uB530\uB77C\uAC00\uAE30");
		btnFriendJoin.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
		btnFriendJoin.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnFriendJoin.setBounds(790, 660, 160, 70);
		friendPanel.add(btnFriendJoin);

		JButton btnFriendDel = new JButton("\uC0AD\uC81C");
		
		btnFriendDel.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
		btnFriendDel.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnFriendDel.setBounds(600, 660, 160, 70);
		friendPanel.add(btnFriendDel);
		dtmInvite = new DefaultTableModel(inviteContent, inviteHeader) {
			public boolean isCellEditable(int rowIndex, int mCollIndex) {
			return false;
			}
			};
		tblInvite = new JTable(dtmInvite);
		tblInvite.getTableHeader().setReorderingAllowed(false); //????????
		tblInvite.getTableHeader().setResizingAllowed(false); //????????????
		tblInvite.setFont(new Font("????Lv2????", Font.PLAIN, 20));infoPanel.setVisible(false);
		
		tblInvite.setFont(new Font("????Lv2????", Font.PLAIN, 20));

		JScrollPane inviteScroll = new JScrollPane(tblInvite);
		inviteScroll.setBounds(30, 120, 350, 510);
		friendPanel.add(inviteScroll);

		JButton btnFriendsBack = new JButton("\u25C1");
		btnFriendsBack.setFont(new Font("????Lv2????", Font.PLAIN, 20));
		btnFriendsBack.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnFriendsBack.setBounds(30, 30, 100, 70);
		friendPanel.add(btnFriendsBack);
		dtmFriends = new DefaultTableModel(friendContent, friendHeader) {
			public boolean isCellEditable(int rowIndex, int mCollIndex) {
			return false;
			}
			};
		tblFriends = new JTable(dtmFriends);
		tblFriends.getTableHeader().setReorderingAllowed(false); //????????
		tblFriends.getTableHeader().setResizingAllowed(false); //????????????
		tblFriends.setFont(new Font("????Lv2????", Font.PLAIN, 20));infoPanel.setVisible(false);
		
		tblFriends.setFont(new Font("????Lv2????", Font.PLAIN, 20));

		JScrollPane friendScroll = new JScrollPane(tblFriends);
		friendScroll.setBounds(450, 120, 500, 510);
		friendPanel.add(friendScroll);

		JLabel rblInfo_1 = new JLabel("\uCE5C\uAD6C\uAD00\uB9AC");
		rblInfo_1.setHorizontalAlignment(SwingConstants.RIGHT);
		rblInfo_1.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 40));
		rblInfo_1.setBounds(350, 30, 600, 70);
		friendPanel.add(rblInfo_1);
		
		txtFriendId = new JTextField();
		txtFriendId.setBounds(0, 0, 116, 21);
		friendPanel.add(txtFriendId);
		txtFriendId.setColumns(10);

		JPanel rankPanel = new JPanel();
		
		rankPanel.setBounds(0, 0, 982, 761);
		mainFrame.getContentPane().add(rankPanel);
		rankPanel.setLayout(null);

		JButton btnRankBack = new JButton("\u25C1");
		
		btnRankBack.setFont(new Font("????Lv2????", Font.PLAIN, 20));
		btnRankBack.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnRankBack.setBounds(30, 30, 100, 70);
		rankPanel.add(btnRankBack);
		dtmRank = new DefaultTableModel(rankContent, rankHeader) {
			public boolean isCellEditable(int rowIndex, int mCollIndex) {
			return false;
			}
			};
		tblRank = new JTable(dtmRank);
		tblRank.getTableHeader().setReorderingAllowed(false); //????????
		tblRank.getTableHeader().setResizingAllowed(false); //????????????
		tblRank.setFont(new Font("????Lv2????", Font.PLAIN, 20));infoPanel.setVisible(false);
		
		tblRank.setFont(new Font("????Lv2????", Font.PLAIN, 20));

		JScrollPane rankScrollPane = new JScrollPane(tblRank);
		rankScrollPane.setBounds(30, 120, 920, 510);
		rankPanel.add(rankScrollPane);

		JLabel rblRank = new JLabel("\uB7AD\uD0B9");
		rblRank.setHorizontalAlignment(SwingConstants.RIGHT);
		rblRank.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 40));
		rblRank.setBounds(350, 30, 600, 70);
		rankPanel.add(rblRank);

		JPanel findPwPanel = new JPanel();
		findPwPanel.setBounds(0, 0, 982, 761);
		mainFrame.getContentPane().add(findPwPanel);
		findPwPanel.setLayout(null);

		JButton btnFindPwBack = new JButton("\u25C1");
		btnFindPwBack.setFont(new Font("????Lv2????", Font.PLAIN, 20));
		btnFindPwBack.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnFindPwBack.setBounds(30, 30, 100, 70);
		findPwPanel.add(btnFindPwBack);

		JLabel lblFindPwId = new JLabel("\uC544\uC774\uB514");
		lblFindPwId.setHorizontalAlignment(SwingConstants.LEFT);
		lblFindPwId.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
		lblFindPwId.setBounds(100, 200, 200, 50);
		findPwPanel.add(lblFindPwId);

		JLabel lblFindPwQ = new JLabel("\uBCF8\uC778\uD655\uC778 \uC9C8\uBB38");
		lblFindPwQ.setHorizontalAlignment(SwingConstants.LEFT);
		lblFindPwQ.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
		lblFindPwQ.setBounds(100, 350, 200, 50);
		findPwPanel.add(lblFindPwQ);

		JLabel lblFindPwA = new JLabel("\uBCF8\uC778\uD655\uC778 \uB2F5\uBCC0");
		lblFindPwA.setHorizontalAlignment(SwingConstants.LEFT);
		lblFindPwA.setFont(new Font("????Lv2???? Bold", Font.PLAIN, 30));
		lblFindPwA.setBounds(100, 500, 200, 50);
		findPwPanel.add(lblFindPwA);

		txtFindPwId = new JTextField();
		txtFindPwId.setFont(new Font("????Lv2????", Font.PLAIN, 20));
		txtFindPwId.setColumns(10);
		txtFindPwId.setBounds(350, 200, 550, 50);
		findPwPanel.add(txtFindPwId);

		txtFindPwQ = new JTextField();
		txtFindPwQ.setFont(new Font("????Lv2????", Font.PLAIN, 20));
		txtFindPwQ.setColumns(10);
		txtFindPwQ.setBounds(350, 350, 550, 50);
		findPwPanel.add(txtFindPwQ);

		txtFindPwA = new JTextField();
		txtFindPwA.setFont(new Font("????Lv2????", Font.PLAIN, 20));
		txtFindPwA.setColumns(10);
		txtFindPwA.setBounds(350, 500, 550, 50);
		findPwPanel.add(txtFindPwA);

		JButton btnFindPwProc = new JButton("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		btnFindPwProc.setFont(new Font("????Lv2????", Font.PLAIN, 20));
		btnFindPwProc.setActionCommand("\uBE44\uBC88\uCC3E\uAE30");
		btnFindPwProc.setBounds(700, 650, 200, 50);
		findPwPanel.add(btnFindPwProc);

		joinPanel.setVisible(false);
		findPwPanel.setVisible(false);
		friendPanel.setVisible(false);
		rankPanel.setVisible(false);
		roomPanel.setVisible(false);
		
		// events
		
		
		//???? ??????
		
		
		//???? ???????? ?????? ??
		friendPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				try {
					//1. ???? ?????? ??????
					ArrayList<FriendsDTO> list = friendsDAO.getFriends(memberDTO.getMemberId());
					if(list != null) { //???? ?????? ?????? ????
						//2. ???? ???? ???????? ?????? ?? ?????? ????
						String friends[][] = new String[list.size()][4];
						int cnt = 0;
						for(FriendsDTO i : list) {
							if(memberDTO.getMemberId().equals(i.getMember1Id())) { // ???? member1????
								friends[cnt][0] = i.getMember2Id(); // member2?? id ????
							}else {
								friends[cnt][0] = i.getMember1Id(); //?????? member1?? ?????? ????
							}
							int state = memberDAO.getState(friends[cnt][0]);
							String temp = "";
							if(0 == state) { //???? ?????? ???? ?????? ????
								temp = "????????";
							}else if(1 == state) {
								temp = "??????";
							}else if(2 == state) {
								temp = "??????";
							}else {
								temp = "??????";
							}
							friends[cnt][1] = temp;
							cnt++;
						}
						
						//3. ???? ???? ???????? ????
						dtmFriends = (DefaultTableModel)tblFriends.getModel();
						dtmFriends.setNumRows(0);
						dtmFriends = new DefaultTableModel(friends, friendHeader){ //???? ???????? ????????
							public boolean isCellEditable(int rowIndex, int mCollIndex) {
								return false;
							}
						};
						tblFriends.setModel(dtmFriends);
					} else { //???? ?????? ???? ?? ????
						//???? ???? ?????? ??????
						dtmFriends = (DefaultTableModel)tblFriends.getModel();
						dtmFriends.setNumRows(0);
						dtmFriends = new DefaultTableModel(friendContent, friendHeader){ //???? ???????? ????????
							public boolean isCellEditable(int rowIndex, int mCollIndex) {
								return false;
							}
						};
					}
					
					
					//1. ???? ???? ?????? ??????
					ArrayList<ReqDTO> list2 = reqDAO.getReqs(memberDTO.getMemberId());
					if(list2 != null) { //???? ?????? ?????? ????
						//2. ????
						String reqs[][] = new String[list2.size()][1];
						int cnt = 0;
						for(ReqDTO i : list2) {
							reqs[cnt][0] = i.getMember2Id(); //???? ???? ?????? ?????? ????
							cnt++;
						}
						
						//3. ???? ???? ???????? ????
						dtmInvite = (DefaultTableModel)tblInvite.getModel();
						dtmInvite.setNumRows(0);
						dtmInvite = new DefaultTableModel(reqs, inviteHeader){ //???? ???????? ????????
							public boolean isCellEditable(int rowIndex, int mCollIndex) {
								return false;
							}
						};
						tblInvite.setModel(dtmInvite);
					}else { //???????? ??????
						//?????? ??????
						dtmInvite = (DefaultTableModel)tblInvite.getModel();
						dtmInvite.setNumRows(0);
						dtmInvite = new DefaultTableModel(inviteContent, inviteHeader){ //???? ???????? ????????
							public boolean isCellEditable(int rowIndex, int mCollIndex) {
								return false;
							}
						};
						tblInvite.setModel(dtmInvite);
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		
		//???? ???? ???? ?????? ??
		btnFriendAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					//?????? ????
					//?????? ??????????, ?????? ??????, 5-12??
					Pattern pattern = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9]{4,11}$");
					String str = txtFriendId.getText();
					Matcher matcher = pattern.matcher(str);
					
					if(matcher.matches()) {//?????? ???? ???? ??
						//1.???????? ????
						if(friendsDAO.reqFriends(str, memberDTO.getMemberId())) {
							//???? ???? ??????
							JOptionPane.showMessageDialog(null, "???? ????");
							txtFriendId.setText(""); //??????
						}else {
							//???? ???? ??????(???? ?????????? ???? ?????? ??????????.)
							JOptionPane.showMessageDialog(null, "???? ?????????? ???? ?????? ??????????.");
						}
					}else {
						JOptionPane.showMessageDialog(null, "???????? 5-12???? ?????? ?????? ????????????.");
					}
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		
		//???? ???? ???? ?????? ??
		btnFriendDel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					//1. ?????? ?????? ?????? ????????
					int col = tblInvite.getSelectedColumn();
					int row = 0;
					String member2Id = String.valueOf(tblFriends.getValueAt(row, col));
					//2. ???? ???? ????
					if(friendsDAO.deleteFriend(memberDTO.getMemberId(), member2Id)) { //???? ??
						JOptionPane.showMessageDialog(null, "???? ????");
						//???? ???????? ???????? ?????? ????
						//1. ???? ?????? ??????
						ArrayList<FriendsDTO> list = friendsDAO.getFriends(memberDTO.getMemberId());
						if(list != null) { //???? ?????? ?????? ????
							//2. ???? ???? ???????? ?????? ?? ?????? ????
							String friends[][] = new String[list.size()][4];
							int cnt = 0;
							for(FriendsDTO i : list) {
								if(memberDTO.getMemberId().equals(i.getMember1Id())) { // ???? member1????
									friends[cnt][0] = i.getMember2Id(); // member2?? id ????
								}else {
									friends[cnt][0] = i.getMember1Id(); //?????? member1?? ?????? ????
								}
								int state = memberDAO.getState(friends[cnt][0]);
								String temp = "";
								if(0 == state) { //???? ?????? ???? ?????? ????
									temp = "????????";
								}else if(1 == state) {
									temp = "??????";
								}else if(2 == state) {
									temp = "??????";
								}else {
									temp = "??????";
								}
								friends[cnt][1] = temp;
								cnt++;
							}
							
							//3. ???? ???? ???????? ????
							dtmFriends = (DefaultTableModel)tblFriends.getModel();
							dtmFriends.setNumRows(0);
							dtmFriends = new DefaultTableModel(friends, friendHeader){ //???? ???????? ????????
								public boolean isCellEditable(int rowIndex, int mCollIndex) {
									return false;
								}
							};
							tblFriends.setModel(dtmFriends);
						} else { //???? ?????? ???? ?? ????
							//???? ???? ?????? ??????
							dtmFriends = (DefaultTableModel)tblFriends.getModel();
							dtmFriends.setNumRows(0);
							dtmFriends = new DefaultTableModel(friendContent, friendHeader){ //???? ???????? ????????
								public boolean isCellEditable(int rowIndex, int mCollIndex) {
									return false;
								}
							};
						}
					}else {
						JOptionPane.showMessageDialog(null, "???? ????");
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		
		//???? ???? ?????? ??
		btnFriendIgnore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					//1. ?????? ?????? ?????? ????????
					int col = tblInvite.getSelectedColumn();
					int row = 0;
					String member2Id = String.valueOf(tblInvite.getValueAt(row, col));
					
					//2. ???? ???? ????
					if(friendsDAO.ignoreFriend(memberDTO.getMemberId(), member2Id)) { //???? ??
						//???? ???????? ???????? ?????? ????
						//1. ???? ?????? ??????
						ArrayList<FriendsDTO> list = friendsDAO.getFriends(memberDTO.getMemberId());
						if(list != null) { //???? ?????? ?????? ????
							//2. ???? ???? ???????? ?????? ?? ?????? ????
							String friends[][] = new String[list.size()][4];
							int cnt = 0;
							for(FriendsDTO i : list) {
								if(memberDTO.getMemberId().equals(i.getMember1Id())) { // ???? member1????
									friends[cnt][0] = i.getMember2Id(); // member2?? id ????
								}else {
									friends[cnt][0] = i.getMember1Id(); //?????? member1?? ?????? ????
								}
								int state = memberDAO.getState(friends[cnt][0]);
								String temp = "";
								if(0 == state) { //???? ?????? ???? ?????? ????
									temp = "????????";
								}else if(1 == state) {
									temp = "??????";
								}else if(2 == state) {
									temp = "??????";
								}else {
									temp = "??????";
								}
								friends[cnt][1] = temp;
								cnt++;
							}
							
							//3. ???? ???? ???????? ????
							dtmFriends = (DefaultTableModel)tblFriends.getModel();
							dtmFriends.setNumRows(0);
							dtmFriends = new DefaultTableModel(friends, friendHeader){ //???? ???????? ????????
								public boolean isCellEditable(int rowIndex, int mCollIndex) {
									return false;
								}
							};
							tblFriends.setModel(dtmFriends);
						} else { //???? ?????? ???? ?? ????
							//???? ???? ?????? ??????
							dtmFriends = (DefaultTableModel)tblFriends.getModel();
							dtmFriends.setNumRows(0);
							dtmFriends = new DefaultTableModel(friendContent, friendHeader){ //???? ???????? ????????
								public boolean isCellEditable(int rowIndex, int mCollIndex) {
									return false;
								}
							};
						}
						//1. ???? ???? ?????? ??????
						ArrayList<ReqDTO> list2 = reqDAO.getReqs(memberDTO.getMemberId());
						if(list2 != null) { //???? ?????? ?????? ????
							//2. ????
							String reqs[][] = new String[list2.size()][1];
							int cnt = 0;
							for(ReqDTO i : list2) {
								reqs[cnt][0] = i.getMember2Id(); //???? ???? ?????? ?????? ????
								cnt++;
							}
							
							//3. ???? ???? ???????? ????
							dtmInvite = (DefaultTableModel)tblInvite.getModel();
							dtmInvite.setNumRows(0);
							dtmInvite = new DefaultTableModel(reqs, inviteHeader){ //???? ???????? ????????
								public boolean isCellEditable(int rowIndex, int mCollIndex) {
									return false;
								}
							};
							tblInvite.setModel(dtmInvite);
						}else { //???????? ??????
							//?????? ??????
							dtmInvite = (DefaultTableModel)tblInvite.getModel();
							dtmInvite.setNumRows(0);
							dtmInvite = new DefaultTableModel(inviteContent, inviteHeader){ //???? ???????? ????????
								public boolean isCellEditable(int rowIndex, int mCollIndex) {
									return false;
								}
							};
							tblInvite.setModel(dtmInvite);
						}
					}else { //???? ??
						JOptionPane.showMessageDialog(null, "???? ????");
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		
		//???? ???? ???? ?????? ??
		btnFriendSure.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					//1. ?????? ?????? ?????? ????????
					int col = tblInvite.getSelectedColumn();
					int row = 0;
					String member2Id = String.valueOf(tblInvite.getValueAt(row, col));
					
					//2. ???? ???? ????
					if(friendsDAO.sureFriendsReq(memberDTO.getMemberId(), member2Id)) {//???? ??
						//???? ???????? ???????? ?????? ????
						//1. ???? ?????? ??????
						ArrayList<FriendsDTO> list = friendsDAO.getFriends(memberDTO.getMemberId());
						if(list != null) { //???? ?????? ?????? ????
							//2. ???? ???? ???????? ?????? ?? ?????? ????
							String friends[][] = new String[list.size()][4];
							int cnt = 0;
							for(FriendsDTO i : list) {
								if(memberDTO.getMemberId().equals(i.getMember1Id())) { // ???? member1????
									friends[cnt][0] = i.getMember2Id(); // member2?? id ????
								}else {
									friends[cnt][0] = i.getMember1Id(); //?????? member1?? ?????? ????
								}
								int state = memberDAO.getState(friends[cnt][0]);
								String temp = "";
								if(0 == state) { //???? ?????? ???? ?????? ????
									temp = "????????";
								}else if(1 == state) {
									temp = "??????";
								}else if(2 == state) {
									temp = "??????";
								}else {
									temp = "??????";
								}
								friends[cnt][1] = temp;
								cnt++;
							}
							
							//3. ???? ???? ???????? ????
							dtmFriends = (DefaultTableModel)tblFriends.getModel();
							dtmFriends.setNumRows(0);
							dtmFriends = new DefaultTableModel(friends, friendHeader){ //???? ???????? ????????
								public boolean isCellEditable(int rowIndex, int mCollIndex) {
									return false;
								}
							};
							tblFriends.setModel(dtmFriends);
						} else { //???? ?????? ???? ?? ????
							//???? ???? ?????? ??????
							dtmFriends = (DefaultTableModel)tblFriends.getModel();
							dtmFriends.setNumRows(0);
							dtmFriends = new DefaultTableModel(friendContent, friendHeader){ //???? ???????? ????????
								public boolean isCellEditable(int rowIndex, int mCollIndex) {
									return false;
								}
							};
						}
						//1. ???? ???? ?????? ??????
						ArrayList<ReqDTO> list2 = reqDAO.getReqs(memberDTO.getMemberId());
						if(list2 != null) { //???? ?????? ?????? ????
							//2. ????
							String reqs[][] = new String[list2.size()][1];
							int cnt = 0;
							for(ReqDTO i : list2) {
								reqs[cnt][0] = i.getMember2Id(); //???? ???? ?????? ?????? ????
								cnt++;
							}
							
							//3. ???? ???? ???????? ????
							dtmInvite = (DefaultTableModel)tblInvite.getModel();
							dtmInvite.setNumRows(0);
							dtmInvite = new DefaultTableModel(reqs, inviteHeader){ //???? ???????? ????????
								public boolean isCellEditable(int rowIndex, int mCollIndex) {
									return false;
								}
							};
							tblInvite.setModel(dtmInvite);
						}else { //???????? ??????
							//?????? ??????
							dtmInvite = (DefaultTableModel)tblInvite.getModel();
							dtmInvite.setNumRows(0);
							dtmInvite = new DefaultTableModel(inviteContent, inviteHeader){ //???? ???????? ????????
								public boolean isCellEditable(int rowIndex, int mCollIndex) {
									return false;
								}
							};
							tblInvite.setModel(dtmInvite);
						}
					}else { //???? ??
						JOptionPane.showMessageDialog(null, "???? ????");
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		
		
		//???? ??????
		
		
		//???? ???????? ?????? ??
		rankPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				//???? ???????? tblRank?? ????
				
				//1.???? 100???? arraylist ????????
				ArrayList<MemberDTO> list = memberDAO.getRanker();
				
				//2. ?????? ???????? 2???????? ??????
				String rankers[][] = new String[list.size()][4];
				int cnt = 0;
				for(MemberDTO i : list) {
					rankers[cnt][0] = String.valueOf(cnt + 1); //????
					rankers[cnt][1] = i.getNickname(); //??????
					rankers[cnt][2] = String.valueOf(i.getExp() / 100);
					rankers[cnt][3] = (i.getWin() + "?? " + i.getLose() + "??"); //????
					cnt++;
				}
				
				//tblRank?? ?? ????
				dtmRank = (DefaultTableModel)tblRank.getModel();
				dtmRank.setNumRows(0);
				dtmRank = new DefaultTableModel(rankers, rankHeader){ //???? ???????? ????????
					public boolean isCellEditable(int rowIndex, int mCollIndex) {
						return false;
					}
				};
				tblRank.setModel(dtmRank);
			}
		});
		
		//???????? ???? ?????? ??
		btnRankBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rankPanel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});
		
		
		//???? ??????
		
		
		//???? ???????? ?????? ??
		mainPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				//mainPanel ??????
				try {
					memberDTO = memberDAO.getMember(memberDTO.getMemberId());//memberDTO ????
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} 
				
				//lblMain ??????
				StringBuffer msg = new StringBuffer();
				msg.append("Lv" + memberDTO.getExp() / 100);
				msg.append(" ");
				msg.append(memberDTO.getNickname() + "?? ??????????");
				lblMain.setText(msg.toString());
				
				//tblMain ??????
				//???? ???????? ?? ???? ???????? ????????
				String[][] rooms;
				try {
					ArrayList<ArrayList<String>> list = roomDAO.getRooms(0);
					//list?? 2???? ?????? ????
					
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
							j=0;
							i++;
						}
					}
					//tblMain?? ?? ????
					dtmMain = (DefaultTableModel)tblMain.getModel();
					dtmMain.setNumRows(0);
					dtmMain = new DefaultTableModel(rooms, header){ //???? ???????? ????????
						public boolean isCellEditable(int rowIndex, int mCollIndex) {
							return false;
						}
					};
					tblMain.setModel(dtmMain);
					
					//???? ???????? ?? ???? ???? ????????, ?????? ???????? ??????
					if(memberDTO.getType() == 0) {
						btnMainDel.setVisible(false);
					}else {
						btnMainDel.setVisible(true);
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		
		//???????? ?????? ?????? ??
		btnMainRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				//tblMain ??????
				//???? ???????? ?? ???? ???????? ????????
				String[][] rooms;
				try {
					ArrayList<ArrayList<String>> list = roomDAO.getRooms(0);
					//list?? 2???? ?????? ????
					
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
							j = 0;
							i++;
						}
					}
					//tblMain?? ?? ????
					dtmMain = (DefaultTableModel)tblMain.getModel();
					dtmMain.setNumRows(0);
					dtmMain = new DefaultTableModel(rooms, header){ //???? ???????? ????????
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
		
		//?? ???? ???? ?????? ??
		btnMainNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//1. ?? ????
				try {
					if(roomDAO.newRoom(memberDTO.getMemberId())) {
						//?? ???? ???? ?? ?? ?? ???? ?????? ?? ???? ????
						roomDTO = new RoomDTO();
						roomDTO.setRoomId(roomDAO.getMyRoom(memberDTO.getMemberId()));
						roomDTO = roomDAO.getRoom(roomDTO.getRoomId());
						
						mainPanel.setVisible(false);
						roomPanel.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "?? ???? ????");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//?? ???? ?????? ??
		btnMainJoin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//?????? ?? ???? ????????
				int col = tblMain.getSelectedColumn();
				int row = 0;
				String temp = String.valueOf(tblMain.getValueAt(row, col));
				System.out.println(temp);
				int roomId = Integer.parseInt(temp);
				try {
					//?? ???? ????
					if(roomDAO.joinMyRoom(memberDTO.getMemberId(), row)) {
						//?????? ?????? ???? ?????? roomPanel???? ????????
						roomDTO = roomDAO.getRoom(roomId); //?????? ???? ?????? ???? ??????
						mainPanel.setVisible(false);
						roomPanel.setVisible(true);
					}else {
						//?????? ?????? ????
						JOptionPane.showMessageDialog(null, "???? ????????. ???????? ?? ??????");
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		//???????? ???? ?????? ??
		btnMainInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainPanel.setVisible(false); //???? ???????? ????????
				infoPanel.setVisible(true); //???????? ???????? ??????
			}
		});
		
		//???? ???? ?????? ??
		btnMainFriends.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainPanel.setVisible(false); //???? ???????? ????????
				friendPanel.setVisible(true); //???? ???????? ??????
			}
		});
		
		//???? ???? ?????? ??
		btnMainRank.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainPanel.setVisible(false); //???? ???????? ????????
				rankPanel.setVisible(true); //???? ???????? ??????
			}
		});
		
		//???????? ???? ?????? ??
		btnMainMatch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					//1. ???? ???????? ?? ???? ????????
					ArrayList<RoomDTO> list = roomDAO.getRoomDTOs(0); //???????? ?? ????????
					if(list.size() > 0) { //???? ?????? ???? ?????? ????
						int index = 0;
						//2. ???????? ?? id ????????
						if(list.size() == 1 ) { //???? ?????? ???? ???????? ???? ???? ???? ?? ?? ????????
							index = list.get(0).getRoomId();
						}else { //???? ???? ???????? id ????????
							Random rnd = new Random();
							index = list.get(rnd.nextInt(list.size() - 1)).getRoomId();
						}
						//3. ???????? ?????? ???? ????
						roomDTO = roomDAO.getRoom(index); //roomDTO ??????
						//?????? ????
						mainPanel.setVisible(false);
						roomPanel.setVisible(true);
					}else { //???? ?????? ???? ??????
						JOptionPane.showMessageDialog(null, "???? ?????? ???? ??????");
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		
		//???????? ??????
		
		//???????? ???????? ?????? ??
		infoPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				try {
					memberDTO = memberDAO.getMember(memberDTO.getMemberId()); //memberDTO?? ????
					
					//???? ??????
					lblInfoNickname2.setText(memberDTO.getNickname());
					lblInfoId2.setText(memberDTO.getMemberId());
					lblInfoLog2.setText(memberDTO.getMatchCount() + "?? " + memberDTO.getWin() + "?? " + memberDTO.getLose() + "??");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//???????? ???? ?????? ???????? ??
		btnInfoChange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!txtInfoPw1.getText().equals(txtInfoPw2.getText())) {
					//?????? ???????????? ??????
					JOptionPane.showMessageDialog(null, "?? ?????????? ????????");
				}else { //?????? ?? ?????????? ??????
					try {
						if(!memberDAO.login(memberDTO.getMemberId(), txtInfoNowPw.getText())) {
							//?????? ???? ?????????? ??????
							//DB?? ?????? ???????? ????
							if(memberDAO.updatePw(memberDTO.getMemberId(), txtInfoPw1.getText())) {
								JOptionPane.showMessageDialog(null, "????????");
							}else {
								JOptionPane.showMessageDialog(null, "????????");
							}
							txtInfoNowPw.setText("");
							txtInfoPw1.setText("");
							txtInfoPw2.setText("");
							infoPanel.setVisible(false);
							mainPanel.setVisible(true); //mainPanel?? ????
						}else { //?????? ???? ?????????? ??????
							JOptionPane.showMessageDialog(null, "???? ?????????? ????????");
							txtInfoNowPw.setText(""); //txtInfoNowPw ??????
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		//???????? ?????? ???????? ??
		btnInfoBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtInfoNowPw.setText("");
				txtInfoPw1.setText("");
				txtInfoPw2.setText("");
				infoPanel.setVisible(false);
				mainPanel.setVisible(true); //?? ?????? ?? mainPanel?? ????
			}
		});
		
		//?????? ??????
		
		//?????? ???????? ?????? ??
		roomPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				//1. ?? ????
				try {
					if(roomDAO.joinMyRoom(memberDTO.getMemberId(), roomDTO.getRoomId())) {
						//1. ?????? ???? ?? ???? ???? ??????, ???? ???? ??????
						txtRoomName.setText(roomDTO.getName());
						if(roomDTO.getRoomId() == roomDAO.getMyRoom(memberDTO.getMemberId())) {
							txtRoomName.setEditable(true);
							btnRoomKick.setVisible(true);
							btnRoomReady.setText("????"); //?????? ????????
						}else {
							//2. ???? ???? ????????
							txtRoomName.setEditable(false);
							btnRoomKick.setVisible(false);
							btnRoomReady.setText("????"); //?????????? ????????
						}
						//?????? ???? ?????? ????????
						ArrayList<ArrayList<String>> list = roomDAO.getMembers(roomDTO.getRoomId());
						String members[][];
						
						//2???? ?????? ????
						if(list != null) {
							members = new String[list.size()][list.get(0).size()];
							int i = 0;
							int j = 0;
							for(ArrayList<String> a : list) {
								for(String b : a) {
									members[i][j++] = b;
								}
								j = 0;
								i++;
							}
						}else {
							members = new String[0][0];
						}
						
						//tblRoom?? ?? ????
						dtmRoom = (DefaultTableModel)tblRoom.getModel();
						dtmRoom.setNumRows(0);
						dtmRoom = new DefaultTableModel(members, roomHeader){ //???? ???????? ????????
							public boolean isCellEditable(int rowIndex, int mCollIndex) {
								return false;
							}
						};
						tblRoom.setModel(dtmRoom);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//???? ???? ?????? ??
		btnRoomBet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				//???? ?????? ???? ????
				if (roomDAO.setBet(roomDTO.getRoomId(), memberDTO.getMemberId())) { //???? ??
					//?????? ???? ?????? ????????
					ArrayList<ArrayList<String>> list = roomDAO.getMembers(roomDTO.getRoomId());
					String members[][];
					
					//2???? ?????? ????
					if(list != null) {
						members = new String[list.size()][list.get(0).size()];
						int i = 0;
						int j = 0;
						for(ArrayList<String> a : list) {
							for(String b : a) {
								members[i][j++] = b;
							}
							j = 0;
							i++;
						}
					}else {
						members = new String[0][0];
					}
					
					//tblRoom?? ?? ????
					dtmRoom = (DefaultTableModel)tblRoom.getModel();
					dtmRoom.setNumRows(0);
					dtmRoom = new DefaultTableModel(members, roomHeader){ //???? ???????? ????????
						public boolean isCellEditable(int rowIndex, int mCollIndex) {
							return false;
						}
					};
					tblRoom.setModel(dtmRoom);
				} else {
					JOptionPane.showMessageDialog(null, "???? ????");
				}
				}catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		
		//????????
		btnRoomReady.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					//?????? ????
					if(roomDTO.getRoomId() == roomDAO.getMyRoom(memberDTO.getMemberId())){
						//1. ?? ???????? ???? ???? ???????? ???? ?? ???? ?????????? ???????? ???? ????
						//2. ???? ????
						//3. ?????? ????
					}else {
						//???? ?????? ???? ?????????? ???????? ???? ????
						if(roomDAO.setReady(memberDTO.getMemberId(), roomDTO.getRoomId())) {
							//???? ???? ??
							//1. ?????? ???? ???? ????????
							//?????? ???? ?????? ????????
							ArrayList<ArrayList<String>> list = roomDAO.getMembers(roomDTO.getRoomId());
							String members[][];
							
							//2???? ?????? ????
							if(list != null) {
								members = new String[list.size()][list.get(0).size()];
								int i = 0;
								int j = 0;
								for(ArrayList<String> a : list) {
									for(String b : a) {
										members[i][j++] = b;
									}
									j = 0;
									i++;
								}
							}else {
								members = new String[0][0];
							}
							
							//tblRoom?? ?? ????
							dtmRoom = (DefaultTableModel)tblRoom.getModel();
							dtmRoom.setNumRows(0);
							dtmRoom = new DefaultTableModel(members, roomHeader){ //???? ???????? ????????
								public boolean isCellEditable(int rowIndex, int mCollIndex) {
									return false;
								}
							};
							tblRoom.setModel(dtmRoom);
							//2. ?????? ??????
							JOptionPane.showMessageDialog(null, "???????? ????");
						}else {
							//?????? ????
							JOptionPane.showMessageDialog(null, "???????? ???? ????");
						}
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		//???? ???? ?????? ?????? ??
		btnRoomName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String name = txtRoomName.getText();
					//?? ???? ???? ????
					if(roomDAO.updateRoomName(roomDTO.getRoomId(), name)) {
						//?????? ????
						txtRoomName.setText(roomDAO.getRoomName(roomDTO.getRoomId()));
						//???? ?????? ????????
						JOptionPane.showMessageDialog(null, "???? ????");
					}else {
						JOptionPane.showMessageDialog(null, "???? ????");
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		//???????? ?????? ?????? ??
		btnRoomRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					//1. ?????? ???? ???? ????????
					//?????? ???? ?????? ????????
					ArrayList<ArrayList<String>> list = roomDAO.getMembers(roomDTO.getRoomId());
					String members[][];
					
					//2???? ?????? ????
					if(list != null) {
						members = new String[list.size()][list.get(0).size()];
						int i = 0;
						int j = 0;
						for(ArrayList<String> a : list) {
							for(String b : a) {
								members[i][j++] = b;
							}
							j = 0;
							i++;
						}
					}else {
						members = new String[0][0];
					}
					
					//tblRoom?? ?? ????
					dtmRoom = (DefaultTableModel)tblRoom.getModel();
					dtmRoom.setNumRows(0);
					dtmRoom = new DefaultTableModel(members, roomHeader){ //???? ???????? ????????
						public boolean isCellEditable(int rowIndex, int mCollIndex) {
							return false;
						}
					};
					tblRoom.setModel(dtmRoom);
					
					//?? ???? ??????
					String name = roomDAO.getRoomName(roomDTO.getRoomId());
					txtRoomName.setText(name); //??????
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//?? ???????? ?????? ??
		btnRoomBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//?? ?????? ????
				try {
					roomDAO.roomOut(memberDTO.getMemberId(), roomDTO.getRoomId());
					//???? ?? ?????? ????
					roomPanel.setVisible(false);
					mainPanel.setVisible(true);
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		
		// ?????? ??????
		
		// ???????? ???? ????
		btnLoginJoin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loginPanel.setVisible(false); //?????? ?????? ????????
				joinPanel.setVisible(true); //???????? ?????? ??????
			}
		});
		
		// ?????? ???? ????
		btnLoginProc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id = txtId.getText();
				String pw = txtPw.getText();
				try {
					if(memberDAO.login(id, pw)) {
						//id?? pw???? ?????? ?????? ???? ????????
						loginPanel.setVisible(false); //???????????? ????????
						memberDTO = memberDAO.getMember(id); //???? ???? ????
						memberDAO.setState(id, 1); //???? ?????? 1(??????)???? ????
						
						mainPanel.setVisible(true); //?????????? ??????
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// ???????? ??????

		// ?????? ???????? ???? ????
		btnJoinIdChk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id = txtJoinId.getText();
				try {
					if (memberDAO.idCheck(id)) {
						txtJoinId.setEnabled(false); // ?????? ?????????? ????????
						btnJoinIdChk.setEnabled(false); // ???????? ???? ????????
						btnJoin.setEnabled(true); // ???????? ???? ??????
						lblJoinIdChk.setText("????????!"); // ???????? ????
					} else {
						lblJoinIdChk.setText("???? ???????? ??????????"); // ???????? ????
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("?????? ???????? ????");
				}
			}
		});
		// ???????? ???? ????
		btnJoinBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					txtJoinId.setEnabled(true);// ?????? ?????????? ??????
					btnJoinIdChk.setEnabled(true);// ???????? ???? ??????
					btnJoin.setEnabled(false);// ???????? ???? ??????
					lblJoinIdChk.setText(""); // ???????? ??????
					loginPanel.setVisible(true); //?????? ?????? ??????
					joinPanel.setVisible(false); //???????? ?????? ????????

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("???????? ?????? ???????? ????");
				}
			}
		});
		// ???????? ???? ????
		btnJoin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// ???????? ?????? ????
				if (txtJoinPwChk.getText().equals(txtJoinPw.getText())) {
					MemberDTO mDTO = new MemberDTO(); // MemberDTO ???? ???? ?? ??????
					mDTO.setMemberId(txtJoinId.getText());
					mDTO.setMemberPw(txtJoinPw.getText());
					mDTO.setNickname(txtJoinNick.getText());
					mDTO.setAnswer(txtJoinA.getText());
					mDTO.setQuestion(txtJoinQ.getText());
					try {
						if (memberDAO.join(mDTO)) {
							txtJoinId.setEnabled(true);// ?????? ?????????? ??????
							btnJoinIdChk.setEnabled(true);// ???????? ???? ??????
							btnJoin.setEnabled(false);// ???????? ???? ??????
							lblJoinIdChk.setText(""); // ???????? ??????
						} else {
							JOptionPane.showMessageDialog(null, "???? ????"); //???????? ?????? ??????
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println("???????? ????");
						JOptionPane.showMessageDialog(null, "???? ????"); //???????? ?????? ??????
					}
				}else {
					JOptionPane.showMessageDialog(null, "?????????? ????????????");
				}
			}
		});
	}
}
