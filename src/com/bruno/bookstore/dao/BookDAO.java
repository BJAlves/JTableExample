package com.bruno.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bruno.bookstore.entity.Book;

public class BookDAO implements BookUtil {
	
	private static final String SQL_INSERT = "insert into BOOKS (PUBLISHING_COMPANY, TITLE, ISBN) values (?,?,?)";
	private static final String SQL_UPDATE = "update LIVROS set EDITORA = ?, TITULO = ?, ISBN = ? where ID = ?";
	private static final String SQL_DELETE = "delete from livros where ID = ?";
	private static final String SQL_FIND_ALL = "select *from livros";
	
	@Override
	public int save(Book book) {
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		int result = 0;
		try {
			statement = connection.prepareStatement(SQL_INSERT);
			statement.setString(1, book.getPublishingCompany());
			statement.setString(2, book.getTitle());
			statement.setString(3, book.getIsbn());
			result = statement.executeUpdate();
		} catch (SQLException e) {
			try {
				if(connection != null) {
					connection.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				DBConnection.close(connection, statement, null);
			}
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(Book book) {
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		int result = 0;
		try {
			statement = connection.prepareStatement(SQL_UPDATE);
			statement.setString(1, book.getPublishingCompany());
			statement.setString(2, book.getTitle());
			statement.setString(3, book.getIsbn());
			statement.setLong(4, book.getId());
			result = statement.executeUpdate();
		} catch (SQLException e) {
			try {
				if(connection != null) {
					connection.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				DBConnection.close(connection, statement, null);
			}
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int remove(Long id) {
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		int result = 0;
		try {
			statement = connection.prepareStatement(SQL_DELETE);
			statement.setLong(1, id);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			try {
				if(connection != null) {
					connection.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				DBConnection.close(connection, statement, null);
			}
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<Book> findAll() {
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		List<Book> books = new ArrayList<>();
		ResultSet rs = null;
		try {
			statement = connection.prepareStatement(SQL_FIND_ALL);
			rs = statement.executeQuery();
			while(rs.next()) {
				Book book = new Book();
				book.setId(rs.getLong("ID"));
				book.setPublishingCompany(rs.getString("PUBLISHING_COMPANY"));
				book.setTitle(rs.getString("TITLE"));
				book.setIsbn(rs.getString("ISBN"));
				books.add(book);
			}
		} catch (SQLException e) {
			try {
				if(connection != null) {
					connection.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				DBConnection.close(connection, statement, null);
			}
			e.printStackTrace();
		}
		
		return books;
	}
	

}
