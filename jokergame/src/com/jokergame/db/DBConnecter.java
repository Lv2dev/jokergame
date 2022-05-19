package com.jokergame.db;

import java.sql.*;

public class DBConnecter {
	private static DBConnecter dbConnecter = new DBConnecter();
	private Connection conn = null;
	private String url = "jdbc:mysql://localhost:3306/jokergame";
	private String id = "root";
	private String pw = "meteo123";

	// 积己磊
	private DBConnecter() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DBConnection OK");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("DBConnection ERROR");
		}
	}

	// DBConnection 教臂沛 按眉 getter
	public static DBConnecter getDBConnecter() {
		if (dbConnecter == null) {
			System.out.println("dbConn 坷幅");
			return null;
		} else {
			return dbConnecter;
		}
	}

	public Connection getConn() {
		return conn;
	}
}
