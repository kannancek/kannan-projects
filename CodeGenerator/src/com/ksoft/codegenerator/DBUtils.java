package com.ksoft.codegenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBUtils {

	
	
	public static Connection getConnection()  {

		Connection conn = null;	

		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String URL = "jdbc:mysql://localhost:3306/fbase";
			String user = "root";
			String pass = "";
			conn =  DriverManager.getConnection(URL,user,pass);		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return conn;

	}

	public static void closeConnection(Connection conn) {

		try {
			if(null != conn)
				conn.close();
		} catch (SQLException e) {
			
		}

	}

	public static void closeResultSet(ResultSet rs){

		try {
			if(null != rs)
				rs.close();
		} catch (SQLException e) {
		}

	}
	
	public static ArrayList<String> getColumnList(String tableName){
		
		
		Connection conn = DBUtils.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rs ;
		ResultSetMetaData md;
		ArrayList<String> colList = new ArrayList<String>();
		
		try {
			
			pstmt = conn.prepareStatement("select * from " + tableName);
			rs = pstmt.executeQuery();			
			md = rs.getMetaData();
			
			int col = md.getColumnCount();
			
			for (int i = 1; i <= col; i++){
				colList.add(md.getColumnName(i));					
			}		
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return colList;
	}
	
	

}
