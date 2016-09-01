package com.haley.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {
	private Connection conn;
	private static MysqlConnection instance;
	public MysqlConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static MysqlConnection getInstance(){
		if(instance == null){
			instance = new MysqlConnection();
		}
		return instance;
	}
	public Connection getConn(){
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/schedule?useUnicode=true&characterEncoding=utf8","root","root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}