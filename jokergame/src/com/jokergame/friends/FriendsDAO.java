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

	// ������
	private FriendsDAO() {

	}

	// getters and setters

	// �ν��Ͻ� getter
	public static FriendsDAO getInstance() {
		if (friendsDAO == null) {
			friendsDAO = new FriendsDAO();
		}
		return friendsDAO;
	}

	// method
	
	// ģ�� ����� �������� �޼���
	
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
			System.out.println("ģ�� ��� �������� ���� " + e.getMessage());
			return null;
		} finally {
			disconnectPstmt();
		}
	}
	
	//ģ�� ��û ������
	//�̹� ģ���̰ų� �ش��ϴ� ���̵� ���� ��� return false
	//member1Id : �޴»��, member2Id : �����»��
	public synchronized boolean reqFriends(String member1Id, String member2Id) throws SQLException{
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();
			
			//1. ���� �޴»�� ���̵� �ִ��� �˻�
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
			
			//2. ģ���� �ƴ� ��� ģ�� ��û
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
			
			if(pstmt.executeUpdate() > 0) { //���� �� true ����
				return true;
			}
			
			return false;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("ģ����û ���� " + e.getMessage());
			return false;
		} finally {
			disconnectPstmt();
		}
	}
	
	//ģ����û ����
	public synchronized boolean sureFriendsReq(String member1Id, String member2Id) throws SQLException{
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();
			
			//friends_request ���̺��� status�� 1�� �����ϰ� ������ ������ friends ���̺� �߰�
			//1. friends_request ���̺��� status�� 1�� �����ϱ�
			query.append("update friends_request set status = 1 ");
			query.append("where member1_id = ? AND member2_id = ? "); // member1Id, member2Id
			
			pstmt = conn.prepareStatement(query.toString());
			
			pstmt.setString(1, member1Id);
			pstmt.setString(2, member2Id);
			
			pstmt.executeUpdate();
			pstmt.close();
			
			//2. �̹� �߰��Ǿ� �ִ��� �˻��ϰ� �ƴ� ��� ������ ������ friends ���̺� �߰��ϱ�
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
			System.out.println("ģ����û �����ϱ� ���� " + e.getMessage());
			return false;
		} finally {
			disconnectPstmt();
		}
	}
	
	//ģ����û ����
}
