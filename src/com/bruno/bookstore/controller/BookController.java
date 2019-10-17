package com.bruno.bookstore.controller;

import com.bruno.bookstore.facade.FacadeBook;
import java.util.List;
import com.bruno.bookstore.entity.Book;

public class BookController {
	private FacadeBook facade;
	
	public BookController() {
		this.facade = new FacadeBook();
	}
	
	public int addBook(Book book) {
		return facade.save(book);
	}
	
	public int updateBook(Book book) {
		return facade.update(book);
	}
	
	public int remove(Long id) {
		return facade.remove(id);
	}
	
	public List<Book> findAll() {
		return facade.findAll();
	}
	
}
