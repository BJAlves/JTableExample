package com.bruno.bookstore;

import com.bruno.bookstore.dao.DBConnection;
import com.bruno.bookstore.form.BookForm;

public class Main {
	public static void main(String[] args) {
		DBConnection.createTable();
		new BookForm();
	}
}
