package com.bruno.bookstore.dao;

import com.bruno.bookstore.entity.Book;
import java.util.List;

public interface BookUtil {
	int save(Book book);
	int update(Book book);
	int remove(Long id);
	List<Book> findAll();
}
