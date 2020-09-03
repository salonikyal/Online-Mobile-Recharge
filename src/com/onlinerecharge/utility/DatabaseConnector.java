package com.onlinerecharge.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector implements Constant {

	/**
	 * 
	 * @return connection
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		Connection conn = null;
		try {
			// Register JDBC driver
			Class.forName(JDBC_DRIVER);
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			return conn;
		} catch (Exception exception) {
			throw exception;
		}
	}
}
