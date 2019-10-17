package com.bruno.bookstore.facade;

import com.bruno.bookstore.dao.BookUtil;

import java.util.List;

import com.bruno.bookstore.dao.BookDAO;
import com.bruno.bookstore.entity.Book;

public class FacadeBook {
	private BookUtil bookUtil;
	
	public FacadeBook() {
		this.bookUtil = new BookDAO();
	}
	
	public int save(Book book) {
		return bookUtil.save(book);
	}
	
	public int update(Book book) {
		return bookUtil.update(book);
	}
	
	public int remove(Long id) {
		return bookUtil.remove(id);
	}
	
	public List<Book> findAll() {
		return bookUtil.findAll();
	}
}
