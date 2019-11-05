package com.bruno.bookstore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/books?useSSL=false";
	private static final String DRIVER_CLASS_MYSQL = "com.mysql.jdbc.Driver";
	private static final String USER = "brunojalves";
	private static final String PASS = "12345678";
	
	public static Connection getConnection() {
		System.out.println("Connecting to the database");
		try {
			Class.forName(DRIVER_CLASS_MYSQL);
			return DriverManager.getConnection(URL_MYSQL, USER, PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;	
	}
	
	public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {	
		try {
			if(connection != null) {
				connection.close();
			}
			
			if(statement != null) {
				statement.close();
			}
			
			if(resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public static void createTable() {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		String sql   =	"CREATE TABLE IF NOT EXISTS books (" +
						"ID BIGINT(20) NOT NULL AUTO_INCREMENT," +
						"PUBLISHING_COMPANY VARCHAR(30) NOT NULL," +
						"TITLE VARCHAR(30) NOT NULL," +
						"ISBN VARCHAR(50) NOT NULL," +
						"PRIMARY KEY (ID)" +
						");";
		try {
			statement = connection.prepareStatement(sql);
			statement.execute();
			System.out.println("Create tables ok!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, statement, null);
		}
	}
}
