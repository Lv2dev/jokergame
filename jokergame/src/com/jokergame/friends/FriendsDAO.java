package com.jokergame.friends;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.jokergame.db.JDBConnect;
import com.jokergame.room.RoomDAO;

public class FriendsDAO extends JDBConnect{
	private static FriendsDAO friendsDAO = new FriendsDAO();

	// 생성자
	private FriendsDAO() {

	}

	// getters and setters

	// 인스턴스 getter
	public static FriendsDAO getInstance() {
		if (friendsDAO == null) {
			friendsDAO = new FriendsDAO();
		}
		return friendsDAO;
	}

	// method
	
	// 친구 목록을 가져오는 메서드
	
	public synchronized ArrayList<FriendsDTO> getFriends(String memberId) throws SQLException{
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();
			
			query.append("select * from friends where member1_id = ? or member2_id = ?");
			pstmt = conn.prepareStatement(query.toString());
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberId);
			
			rs = pstmt.executeQuery();
			
			ArrayList<FriendsDTO> list = new ArrayList<FriendsDTO>();
			while(rs.next()) {
				FriendsDTO dto = new FriendsDTO();
				dto.setId(rs.getInt("id"));
				dto.setMember1Id(rs.getString("member1_id"));
				dto.setMember2Id(rs.getString("member2_id"));
				dto.setDate(rs.getDate("date"));
				dto.setState(rs.getInt("state"));
				list.add(dto);
			}
			
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("친구 목록 가져오기 오류 " + e.getMessage());
			return null;
		} finally {
			disconnectPstmt();
		}
	}
	
	//친구 요청 보내기
	//이미 친구이거나 해당하는 아이디가 없을 경우 return false
	//member1Id : 받는사람, member2Id : 보내는사람
	public synchronized boolean reqFriends(String member1Id, String member2Id) throws SQLException{
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();
			
			//1. 먼저 받는사람 아이디가 있는지 검사
			query.append("select member_id from member where member_id = ?");
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, member1Id);
			rs = pstmt.executeQuery();
			int cnt = 0;
			while(rs.next()) {
				cnt++;
			}
			if(cnt == 0) {
				return false;
			}
			
			//2. 친구가 아닌 경우 친구 요청
			pstmt.close();
			query = new StringBuffer();
			query.append("insert into friends_request(member1_id, member2_id, status, date) ");
			query.append("SELECT ?, ?, 0, ? FROM DUAL WHERE NOT EXISTS "); //member1Id, member2Id,timestamp
			query.append("(SELECT id FROM friends WHERE (member1_id = ? AND member2_id = ?) OR (member2_id = ? AND member1_id = ?))");
			//member1Id, member2Id, member1Id, member2Id
			pstmt = conn.prepareStatement(query.toString());
			
			pstmt.setString(1, member1Id);
			pstmt.setString(2, member2Id);
			
			//timestamp
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Calendar cal = Calendar.getInstance();
			String today = null;
			today = formatter.format(cal.getTime());
			Timestamp ts = Timestamp.valueOf(today);
			
			pstmt.setTimestamp(3, ts);
			
			pstmt.setString(4, member1Id);
			pstmt.setString(5, member2Id);
			pstmt.setString(6, member1Id);
			pstmt.setString(7, member2Id);
			
			if(pstmt.executeUpdate() > 0) { //성공 시 true 리턴
				return true;
			}
			
			return false;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("친구요청 오류 " + e.getMessage());
			return false;
		} finally {
			disconnectPstmt();
		}
	}
	
	//친구요청 수락
	public synchronized boolean sureFriendsReq(String member1Id, String member2Id) throws SQLException{
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();
			
			//friends_request 테이블의 status를 1로 변경하고 수락한 내용을 friends 테이블에 추가
			//1. friends_request 테이블의 status를 1로 변경하기
			query.append("update friends_request set status = 1 ");
			query.append("where member1_id = ? AND member2_id = ? "); // member1Id, member2Id
			
			pstmt = conn.prepareStatement(query.toString());
			
			pstmt.setString(1, member1Id);
			pstmt.setString(2, member2Id);
			
			pstmt.executeUpdate();
			pstmt.close();
			
			//2. 이미 추가되어 있는지 검사하고 아닌 경우 수락한 내용을 friends 테이블에 추가하기
			query = new StringBuffer();
			
			query.append("insert into friends(member1_id, member2_id, date, state) ");
			query.append("SELECT ?, ?, ?, 0 FROM DUAL WHERE NOT EXISTS "); //member1Id, member2Id, timestamp
			query.append("(SELECT id FROM friends WHERE (member1_id = ? AND member2_id = ?) OR (member2_id = ? AND member1_id = ?))");
			
			pstmt = conn.prepareStatement(query.toString());
			
			pstmt.setString(1, member1Id);
			pstmt.setString(2, member2Id);
			
			//timestamp
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Calendar cal = Calendar.getInstance();
			String today = null;
			today = formatter.format(cal.getTime());
			Timestamp ts = Timestamp.valueOf(today);
			
			pstmt.setTimestamp(3, ts);
			
			pstmt.setString(4, member1Id);
			pstmt.setString(5, member2Id);
			pstmt.setString(6, member1Id);
			pstmt.setString(7, member2Id);
			
			if(pstmt.executeUpdate() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("친구요청 수락하기 오류 " + e.getMessage());
			return false;
		} finally {
			disconnectPstmt();
		}
	}
	
	//친구요청 거절
}
