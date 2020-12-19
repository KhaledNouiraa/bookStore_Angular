package org.polytec.vermeg.service;

import java.util.List;

import org.polytec.vermeg.dao.BookDAO;
import org.polytec.vermeg.dao.BookDAOImp;
import org.polytec.vermeg.model.Book;
import org.polytec.vermeg.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("bookService")
public class BookServiceImp implements BookService{

	@Autowired
	BookDAO bookDao ;
	@Override
	@Transactional
	public List<Book> getAllBooks() {
		return bookDao.getBooks();
	}
	@Override
	@Transactional
	public Book getBook(int id) {
		return bookDao.getBook(id);
	}
	@Override
	@Transactional
	public void saveBook(Book book) {

		bookDao.saveBook(book);
	}
	@Override
	@Transactional
	public double getPriceBook(int id) {
		return bookDao.getPriceBook(id);
	}
	@Override
	@Transactional
	public void deleteBook(int id) {
		bookDao.deleteBook(id);
	}
}
