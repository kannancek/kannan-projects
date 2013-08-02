package com.ksoft.core.utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

import com.ksoft.core.exception.InternalException;
import com.ksoft.core.logger.KsoftLogger;

public class DBUtils {

	
	private static BasicDataSource  dataSource = new BasicDataSource ();
	
	public static void initialize(String strConfigFile) throws InternalException {

		try {
			Properties datasourceFile = new Properties();
			
			datasourceFile
					.load(new FileInputStream(strConfigFile));
			
			
			
			  dataSource.setUsername(datasourceFile.getProperty("username"));
			  dataSource.setPassword(datasourceFile.getProperty("password"));
			  dataSource.setUrl(datasourceFile.getProperty("connectionUrl"));
			  dataSource.setDriverClassName(datasourceFile.getProperty("driverName"));
						 
			 

		} catch (Exception e) {
			
			KsoftLogger.getLogger().error(e.getMessage());
			throw new InternalException(101, e.getMessage());

		} 

	}

	public static Connection getConnection() throws InternalException {

		Connection conn = null;	

		try {
			conn = dataSource.getConnection();			
		} catch (SQLException e) {
			KsoftLogger.getLogger().error(e.getMessage());
			throw new InternalException(102, e.getMessage());
		}

		return conn;

	}

	public static void closeConnection(Connection conn) {

		try {
			if(null != conn)
				conn.close();
		} catch (SQLException e) {
			KsoftLogger.getLogger().error("Error occurred while closing connection");
			
		}

	}

	public static void closeResultSet(ResultSet rs){

		try {
			if(null != rs)
				rs.close();
		} catch (SQLException e) {
			KsoftLogger.getLogger().error("Error occurred while closing result set");
		}

	}
	
	public static void closeStatment(Statement stmt){

		try {
			if(null != stmt)
				stmt.close();
		} catch (SQLException e) {
			KsoftLogger.getLogger().error("Error occurred while closing statement");
		}

	}

}
