package com.jokergame.db;

import java.sql.*;

//DAO�� ���� �θ� Ŭ����
public class JDBConnect {
	protected DBConnecter dbConn = DBConnecter.getDBConnecter();

	protected Connection conn;
	protected Statement stmt;
	protected ResultSet rs;
	protected PreparedStatement pstmt;
	protected StringBuffer query;
	
	// pstmt�� ��� ���� ����
	protected void disconnectPstmt() throws SQLException {
		if (rs != null) {
			rs.close();
		}
		pstmt.close();
	}

	// stmt�� ��� ���� ����
	protected void disconnectStmt() throws SQLException {
		if (rs != null) {
			rs.close();
		}
		stmt.close();
	}
}