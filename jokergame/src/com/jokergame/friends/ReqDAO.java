package com.jokergame.friends;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jokergame.db.JDBConnect;

public class ReqDAO extends JDBConnect{
	private static ReqDAO reqDAO = new ReqDAO();

	// 생성자
	private ReqDAO() {

	}

	// getters and setters

	// 인스턴스 getter
	public static ReqDAO getInstance() {
		if (reqDAO == null) {
			reqDAO = new ReqDAO();
		}
		return reqDAO;
	}

	// method
	
	//받은 요청 목록을 가져오는 메서드
	
	public synchronized ArrayList<ReqDTO> getReqs(String memberId) throws SQLException{
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();
			
			query.append("select * from friends_request where member1_id = ? AND status = 0");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			ArrayList<ReqDTO> list = new ArrayList<ReqDTO>();
			int cnt = 0;
			while(rs.next()) {
				ReqDTO dto = new ReqDTO();
				dto.setId(rs.getInt("id"));
				dto.setDate(rs.getDate("date"));
				dto.setMember1Id(rs.getString("member1_id"));
				dto.setMember2Id(rs.getString("member2_id"));
				dto.setStatus(rs.getInt("status"));
				cnt++;
				list.add(dto);
			}
			
			if(cnt == 0) {
				return null;
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("친구요청 목록 가져오기 오류 " + e.getMessage());
			return null;
		} finally {
			disconnectPstmt();
		}
	}
}
