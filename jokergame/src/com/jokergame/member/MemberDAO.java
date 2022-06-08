package com.jokergame.member;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.jokergame.db.JDBConnect;

public class MemberDAO extends JDBConnect{
	private static MemberDAO memberDAO = new MemberDAO();

	// 생성자
	private MemberDAO() {

	}

	// getters and setters

	// 인스턴스 getter
	public static MemberDAO getInstance() {
		if (memberDAO == null) {
			memberDAO = new MemberDAO();
		}
		return memberDAO;
	}
	
	//method
	
	//회원가입
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
			pstmt.setTimestamp(4, ts);//현재 시간을 기록
			pstmt.setInt(5, memberDTO.getExp());
			pstmt.setInt(6, memberDTO.getWin());
			pstmt.setInt(7, memberDTO.getLose());
			pstmt.setInt(8, memberDTO.getMatchCount()); //현재 시간을 기록
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
	
	
	//아이디 중복체크
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
			System.out.println("MemberDAO_아이디중복체크에러");
			return false;
		} finally {
			disconnectPstmt();
		}
	}
	
	//로그인
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
			System.out.println("로그인 에러");
			return false;
		} finally {
			disconnectPstmt();
		}
	}
	
	//회원정보 MemberDTO로 가져오기
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
			System.out.println("회원정보 가져오기 오류");
			return null;
		} finally {
			disconnectPstmt();
		}
	}
	
	//회원의 상태 변경하기
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
			System.out.println("회원 상태 변경 에러");
			return false;
		} finally {
			disconnectPstmt();
		}
	}
	
	//비밀번호 수정하기
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
			System.out.println("비밀번호 변경 에러" + e.getMessage());
			return false;
		} finally {
			disconnectPstmt();
		}
	}
	
	//랭킹 top 100 가져오기
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
			System.out.println("랭커 가져오기 오류 " + e.getMessage());
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
	
	//member_id로 state 가져오기
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
			System.out.println("member_id로 state 가져오기 오류 " + e.getMessage());
			return 99;
		} finally {
			disconnectPstmt();
		}
	}
}
