package org.polytec.vermeg.service;

import java.util.List;

import org.polytec.vermeg.model.Book;

public interface BookService {

	public List<Book> getAllBooks() ;
	
	public Book getBook(int id) ;
	
	public void saveBook(Book book)  ;
	
	public double getPriceBook(int id) ;
	
	public void deleteBook(int id);
	
	
}
