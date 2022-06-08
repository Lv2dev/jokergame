package com.jokergame.member;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.jokergame.db.JDBConnect;

public class MemberDAO extends JDBConnect{
	private static MemberDAO memberDAO = new MemberDAO();

	// ������
	private MemberDAO() {

	}

	// getters and setters

	// �ν��Ͻ� getter
	public static MemberDAO getInstance() {
		if (memberDAO == null) {
			memberDAO = new MemberDAO();
		}
		return memberDAO;
	}
	
	//method
	
	//ȸ������
	public synchronized boolean join(MemberDTO memberDTO) throws SQLException {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Calendar cal = Calendar.getInstance();
			String today = null;
			today = formatter.format(cal.getTime());
			Timestamp ts = Timestamp.valueOf(today);
			
			conn = dbConn.getConn();
			query = new StringBuffer();
			query.append("INSERT INTO member ");
			query.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmt = conn.prepareStatement(query.toString());

			pstmt.setString(1, memberDTO.getMemberId());
			pstmt.setString(2, memberDTO.getMemberPw());
			pstmt.setString(3, memberDTO.getNickname());
			pstmt.setTimestamp(4, ts);//���� �ð��� ���
			pstmt.setInt(5, memberDTO.getExp());
			pstmt.setInt(6, memberDTO.getWin());
			pstmt.setInt(7, memberDTO.getLose());
			pstmt.setInt(8, memberDTO.getMatchCount()); //���� �ð��� ���
			pstmt.setString(9, memberDTO.getQuestion());
			pstmt.setString(10, memberDTO.getAnswer());
			pstmt.setInt(11, memberDTO.getType());
			pstmt.setInt(12, memberDTO.getState());

			if (pstmt.executeUpdate() != 1) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("memberDAO_memberJoin()_ERROR");
			return false;
		} finally {
			disconnectPstmt();
		}
	}
	
	
	//���̵� �ߺ�üũ
	public synchronized boolean idCheck(String id) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();

			query.append("SELECT member_id FROM member ");
			query.append("WHERE member_id = '" + id + "'");

			pstmt = conn.prepareStatement(query.toString());
			rs = pstmt.executeQuery();

			int cnt = 0;
			while (rs.next()) {
				cnt++;
			}

			if (cnt != 0) {
				return false;
			}

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.out.println("MemberDAO_���̵��ߺ�üũ����");
			return false;
		} finally {
			disconnectPstmt();
		}
	}
	
	//�α���
	public synchronized boolean login(String id, String pw) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();

			query.append("SELECT member_id, member_pw FROM member ");
			query.append("WHERE member_id = ? AND member_pw = ?");

			pstmt = conn.prepareStatement(query.toString());
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			int cnt = 0;
			while (rs.next()) {
				cnt++;
			}

			if (cnt == 1) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�α��� ����");
			return false;
		} finally {
			disconnectPstmt();
		}
	}
	
	//ȸ������ MemberDTO�� ��������
	public synchronized MemberDTO getMember(String id) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();

			query.append("SELECT * FROM member ");
			query.append("WHERE member_id = ?");

			pstmt = conn.prepareStatement(query.toString());
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			MemberDTO memberDTO = new MemberDTO();
			int cnt = 0;
			while (rs.next()) {
				memberDTO.setMemberId(id);
				memberDTO.setNickname(rs.getString("nickname"));
				memberDTO.setJoinDay(null);
				memberDTO.setExp(rs.getInt("exp"));
				memberDTO.setWin(rs.getInt("win"));
				memberDTO.setLose(rs.getInt("lose"));
				memberDTO.setMatchCount(rs.getInt("match_count"));
				cnt++;
			}

			if (cnt == 1) {
				return memberDTO;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ȸ������ �������� ����");
			return null;
		} finally {
			disconnectPstmt();
		}
	}
	
	//ȸ���� ���� �����ϱ�
	public synchronized boolean setState(String id, int state) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();

			query.append("UPDATE member ");
			query.append("SET state = ? ");
			query.append("WHERE member_id = ? ");

			pstmt = conn.prepareStatement(query.toString());
			
			pstmt.setInt(1, state);
			pstmt.setString(2, id);
			
			pstmt.executeUpdate();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ȸ�� ���� ���� ����");
			return false;
		} finally {
			disconnectPstmt();
		}
	}
	
	//��й�ȣ �����ϱ�
	public synchronized boolean updatePw(String id, String pw) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();

			query.append("UPDATE member ");
			query.append("SET member_pw = ? ");
			query.append("WHERE member_id = ? ");

			pstmt = conn.prepareStatement(query.toString());
			
			pstmt.setString(1, pw);
			pstmt.setString(2, id);
			
			pstmt.executeUpdate();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��й�ȣ ���� ����" + e.getMessage());
			return false;
		} finally {
			disconnectPstmt();
		}
	}
	
	//��ŷ top 100 ��������
	public synchronized ArrayList<MemberDTO> getRanker(){
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();
			
			query.append("SELECT * FROM member order by exp desc limit 100");
			
			pstmt = conn.prepareStatement(query.toString());
			
			rs = pstmt.executeQuery();
			
			ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setMemberId(rs.getString("member_id"));
				dto.setExp(rs.getInt("exp"));
				dto.setNickname(rs.getString("nickname"));
				dto.setWin(rs.getInt("win"));
				dto.setLose(rs.getInt("lose"));
				list.add(dto);
			}
			return list;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("��Ŀ �������� ���� " + e.getMessage());
			return null;
		}finally {
			try {
				disconnectPstmt();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//member_id�� state ��������
	public synchronized int getState(String memberId) throws SQLException{
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();
			query.append("select state from member where member_id = ?");
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			int state = 0;
			while(rs.next()) {
				state = rs.getInt("state");
				cnt++;
			}
			
			if(cnt == 0) {
				return 99;
			}
			
			return state;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("member_id�� state �������� ���� " + e.getMessage());
			return 99;
		} finally {
			disconnectPstmt();
		}
	}
}
