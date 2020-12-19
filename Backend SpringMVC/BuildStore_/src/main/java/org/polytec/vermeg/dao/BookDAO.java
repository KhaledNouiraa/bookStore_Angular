package org.polytec.vermeg.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.polytec.vermeg.model.Book;

public interface BookDAO {

	public void setSessionFactory(SessionFactory sf) ;
	
	public List<Book> getBooks() ;
	
	public Book getBook(int id) ;
	
	public void saveBook(Book book) ;
	
	public void deleteBook(int id) ;
	
	public double getPriceBook(int id) ;

}
