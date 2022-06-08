package com.jokergame.room;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.jokergame.db.JDBConnect;
import com.jokergame.member.MemberDAO;
import com.jokergame.member.MemberDTO;

public class RoomDAO extends JDBConnect {
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

	// method

	// ���� ���� �� 2���� �迭�� �������� (0:����� 1:������)
	public synchronized ArrayList<ArrayList<String>> getRooms(int status) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();

			query.append(
					"select * from (SELECT r.room_id, r.name, m.nickname, COUNT(rm.member_id) as cnt  FROM room as r inner join member as m ");
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

			if (cnt == 0) {
				return null; // ����� ������ null����
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

	// �� ����
	public synchronized boolean newRoom(String id) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();
			query.append("INSERT INTO room(`name`, `member_id`, `status`) ");
			query.append("VALUES(?,?,?)");

			pstmt = conn.prepareStatement(query.toString());

			pstmt.setString(1, "�̸����� ��");
			pstmt.setString(2, id);
			pstmt.setInt(3, 0); // 0:�����

			if (pstmt.executeUpdate() != 1) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�� ���� ����" + e.getMessage());
			return false;
		} finally {
			disconnectPstmt();
		}
	}

	// ���� ������ ���� id ��������
	public synchronized int getMyRoom(String id) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();

			query.append("SELECT room_id FROM room WHERE member_id = ? AND status = ?");

			pstmt = conn.prepareStatement(query.toString());

			pstmt.setString(1, id);
			pstmt.setInt(2, 0); // 0:�����

			rs = pstmt.executeQuery();

			int roomId = 0;
			int cnt = 0;
			while (rs.next()) {
				roomId = rs.getInt("room_id");
				cnt++;
			}

			if (cnt == 0) {
				return 0; // ����� ������ 0 ����
			}
			return roomId;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�� ���� �������� ����" + e.getMessage());
			return 0;
		} finally {
			disconnectPstmt();
		}
	}

	// �� ����
	// 1. ���� �ο��� ���� ���� �ʾҴ��� üũ
	// 2. �����Ű��
	public synchronized boolean joinMyRoom(String memberId, int roomId) throws SQLException {
		try {
			conn = dbConn.getConn();

			query = new StringBuffer();
			// roomId, memberId
			query.append(
					"insert into room_member(room_id, member_id) select ?, ? from DUAL where EXISTS(select * from ");
			query.append("(select count(rm.member_id) as cnt from room as r ");
			query.append("inner join room_member as rm on r.room_id = rm.room_id) as mmm where cnt < 6) ");

			pstmt = conn.prepareStatement(query.toString());

			pstmt.setInt(1, roomId);
			pstmt.setString(2, memberId);

			if (pstmt.executeUpdate() != 1) {
				return false; // ���� �� á���� ���ϵ�
			}

			pstmt.close();

			// �� �ο� 0���� ��� ������ ��� ������ ������� �ٲٴ� �κ� �߰�
			query = new StringBuffer();

			query.append("update room ");
			query.append("set member_id = ? "); // memberId
			query.append("where (SELECT count(id) FROM room_member WHERE room_id = ?) = 0 "); // roomId
			pstmt = conn.prepareStatement(query.toString());

			pstmt.setString(1, memberId);
			pstmt.setInt(2, roomId);

			pstmt.executeUpdate();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�� ���� ����" + e.getMessage());
			return false;
		} finally {
			disconnectPstmt();
		}
	}

	// �� ���� ��������
	public synchronized RoomDTO getRoom(int roomId) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();

			query.append("SELECT * FROM room WHERE room_id = ?");

			pstmt = conn.prepareStatement(query.toString());

			pstmt.setInt(1, roomId);

			rs = pstmt.executeQuery();

			RoomDTO roomDTO = new RoomDTO();
			int cnt = 0;
			while (rs.next()) {
				roomDTO.setMemberId(rs.getString("member_Id"));
				roomDTO.setName(rs.getString("name"));
				roomDTO.setRoomId(rs.getInt("room_id"));
				roomDTO.setStatus(rs.getInt("status"));
				cnt++;
			}

			if (cnt == 0) {
				return null; // ����� ������ 0 ����
			}
			return roomDTO;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�� ���� �������� ����" + e.getMessage());
			return null;
		} finally {
			disconnectPstmt();
		}
	}

	// �ش� ���� �ο��� ��������
	public synchronized ArrayList<ArrayList<String>> getMembers(int roomId) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();

			// ����, �г���, ���ð���ġ, �غ����
			query.append("SELECT m.exp, m.member_id, rm.bet_exp, rm.ready ");
			query.append("FROM member as m INNER JOIN room_member as rm ");
			// roomId
			query.append("ON m.member_id = rm.member_id and rm.room_id = ? ");

			pstmt = conn.prepareStatement(query.toString());

			pstmt.setInt(1, roomId);

			rs = pstmt.executeQuery();

			// ��ȯ�� 2���� ArrayList ����
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			int cnt = 0;
			while (rs.next()) {
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(rs.getString("exp"));
				temp.add(rs.getString("member_id"));
				temp.add(String.valueOf(rs.getInt("bet_exp")));
				temp.add(String.valueOf(rs.getInt("ready")));
				list.add(temp);
				cnt++;
			}

			if (cnt == 0) {
				return null; // ����� ������ 0 ����
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�� �ο� �������� ����" + e.getMessage());
			return null;
		} finally {
			disconnectPstmt();
		}
	}

	// �� ������ - ���� �ο��� 0�� �� �浵 �̾ ����
	public synchronized boolean roomOut(String memberId, int roomId) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();

			query.append("delete from room_member where member_id = ? ");
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, memberId);

			if (pstmt.executeUpdate() > 0) {
				pstmt.close();

				query = new StringBuffer();
				query.append("delete from room where (select count(id) from room_member where room_id = ?) = 0");
				pstmt = conn.prepareStatement(query.toString());
				pstmt.setInt(1, roomId);
				pstmt.executeUpdate();

				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("�� ������ ���� " + e.getMessage());
			return false;
		} finally {
			disconnectPstmt();
		}
	}

	// �� �̸� ��������
	public synchronized String getRoomName(int roomId) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();
			query.append("select name from room where room_id = ?"); // roomId
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, roomId);
			rs = pstmt.executeQuery();

			String name = "";
			while (rs.next()) {
				name = rs.getString("name");
			}

			return name;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("�� �̸� �������� ���� " + e.getMessage());
			return null;
		} finally {
			disconnectPstmt();
		}
	}

	// �� �̸� �����ϱ�
	public synchronized boolean updateRoomName(int roomId, String roomName) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();
			query.append("update room set ");
			query.append("name = ? ");// roomName
			query.append("where room_id = ? ");// roomId

			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, roomName);
			pstmt.setInt(2, roomId);
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
			return false;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("�� �̸� ���� ���� " + e.getMessage());
			return false;
		} finally {
			disconnectPstmt();
		}
	}

	// ������ �غ���¸� �����ϱ�
	public synchronized boolean setReady(String memberId, int roomId) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();
			query.append("update room_member set ");
			query.append("ready = case ");
			query.append("when ready = 0 then 1 ");
			query.append("when ready = 1 then 0 ");
			query.append("else ready end ");
			query.append("where member_id = ? AND room_id = ?  ");

			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, memberId);
			pstmt.setInt(2, roomId);

			if (pstmt.executeUpdate() > 0) {
				return true;
			}

			return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("�غ���� ���� ���� " + e.getMessage());
			return false;
		} finally {
			disconnectPstmt();
		}
	}

	// �����ִ� ���� roomDTO���� �������� �޼���
	public synchronized ArrayList<RoomDTO> getRoomDTOs(int state) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();
			query.append("SELECT * FROM room WHERE status = ?");
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, state);

			rs = pstmt.executeQuery();
			ArrayList<RoomDTO> list = new ArrayList<RoomDTO>();
			while (rs.next()) {
				RoomDTO dto = new RoomDTO();
				dto.setMemberId(rs.getString("member_id"));
				dto.setName(rs.getString("name"));
				dto.setRoomId(rs.getInt("room_id"));
				dto.setStatus(rs.getInt("status"));
				list.add(dto);
			}

			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("RoomDTO���� �������� �޼��� ���� " + e.getMessage());
			return null;
		} finally {
			disconnectPstmt();
		}
	}

	// ���� ��ư ������
	// �ѹ� ������ 10 �ö󰡰� �ִ� 100���� �ö�, 100���� �ѹ� �� ������ 10���� ���ư�
	// �����ִ� ���� roomDTO���� �������� �޼���
	public synchronized boolean setBet(int bet, int roomId, String memberId) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();
			query.append("UPDATE room_member SET bet_exp =  ");
			query.append("CASE bet_exp < 100 THEN bet_exp + 10 "); //������ ���ð���ġ�� 100�̸��̸� 10�� ����
			query.append("ELSE 10 "); //�ƴ� ��� bet_exp�� 10���� ������Ʈ
			query.append("WHERE room_id = ? AND member_id = ? ");
			//roomId, memberId
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, roomId);
			pstmt.setString(2, memberId);

			if(0 == pstmt.executeUpdate()) {
				return false;
			}

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("���� ��ư ������ ���� " + e.getMessage());
			return false;
		} finally {
			disconnectPstmt();
		}
	}
}
