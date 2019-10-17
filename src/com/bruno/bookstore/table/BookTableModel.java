package com.bruno.bookstore.table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.bruno.bookstore.entity.Book;


public class BookTableModel extends AbstractTableModel {
	private static final int COL_ID = 0;
	private static final int COL_PUBLISHING_COMPANY = 1;
	private static final int COL_TITLE = 2;
	private static final int COL_ISBN = 3;
	
	private List<Book> values;
	
	public BookTableModel(List<Book> values) {
		this.values = values;
	}

	@Override
	public int getRowCount() {
		return this.values.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Book book = values.get(rowIndex);
		switch(columnIndex) {
			case COL_ID:
				return book.getId();
			
			case COL_PUBLISHING_COMPANY:
				return book.getPublishingCompany();
			
			case COL_TITLE:
				return book.getTitle();
			
			case COL_ISBN:
				return book.getIsbn();		
		}
		return null;
	}
	
	@Override
	public String getColumnName(int column) {
		String columnStr = "";
		switch(column) {
			case COL_ID:
				columnStr = "Code";
				return columnStr;
			case COL_PUBLISHING_COMPANY:
				columnStr = "Publishing company";
				return columnStr;
			case COL_TITLE:
				columnStr = "Title";
				return columnStr;
			case COL_ISBN:
				columnStr = "ISBN";
				return columnStr;
			default:
				throw new IllegalArgumentException("Invalid column");
		}
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex) {
			case COL_ID:
				return Long.class;
			case COL_PUBLISHING_COMPANY:
			case COL_TITLE:
			case COL_ISBN:
				return String.class;
		}
		return null;
	}
	
	public Book get(int row) {
		return values.get(row);	
	}
	
}
