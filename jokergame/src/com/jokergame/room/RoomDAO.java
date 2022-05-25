package com.jokergame.room;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jokergame.db.JDBConnect;
import com.jokergame.member.MemberDAO;
import com.jokergame.member.MemberDTO;

public class RoomDAO extends JDBConnect{
	private static RoomDAO roomDAO = new RoomDAO();

	// ������
	private RoomDAO() {

	}

	// getters and setters

	// �ν��Ͻ� getter
	public static RoomDAO getInstance() {
		if (roomDAO == null) {
			roomDAO = new RoomDAO();
		}
		return roomDAO;
	}
	
	//method
	
	
	//���� ���� �� 2���� �迭�� �������� (0:����� 1:������)
	public synchronized ArrayList<ArrayList<String>> getRooms(int status) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();

			query.append("select * from (SELECT r.room_id, r.name, m.nickname, COUNT(rm.member_id) as cnt  FROM room as r inner join member as m ");
			query.append("on r.member_id = m.member_id ");
			query.append("inner join room_member as rm on rm.room_id = r.room_id ");
			query.append("and r.status = ?) as rooms where cnt > 0 ");

			pstmt = conn.prepareStatement(query.toString());
			
			pstmt.setInt(1, status);
			
			rs = pstmt.executeQuery();
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			
			int cnt = 0;
			while (rs.next()) {
				ArrayList<String> room = new ArrayList<String>();
				room.add(String.valueOf(rs.getInt("room_id")));
				room.add(rs.getString("name"));
				room.add(rs.getString("nickname"));
				room.add(String.valueOf(rs.getInt("cnt")));
				list.add(room);
				cnt++;
				System.out.println(rs.getInt("room_id"));
			}
			
			if(cnt == 0) {
				return null; //����� ������ null����
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�� ���� �������� ����" + e.getMessage());
			return null;
		} finally {
			disconnectPstmt();
		}
	}
}
