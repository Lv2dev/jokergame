package com.jokergame.member;

import java.sql.*;
import java.text.SimpleDateFormat;
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
}
