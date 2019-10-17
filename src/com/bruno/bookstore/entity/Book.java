package com.bruno.bookstore.entity;

public class Book {
	
	private Long id;
	private String publishingCompany;
	private String title;
	private String isbn;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPublishingCompany() {
		return publishingCompany;
	}
	public void setPublishingCompany(String publishingCompany) {
		this.publishingCompany = publishingCompany;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
