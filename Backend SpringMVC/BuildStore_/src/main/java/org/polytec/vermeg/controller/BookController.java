package org.polytec.vermeg.controller;

import java.util.List;

import org.hibernate.annotations.Polymorphism;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.polytec.vermeg.model.Book;
import org.polytec.vermeg.model.Customer;
import org.polytec.vermeg.service.BookService;
import org.polytec.vermeg.service.BookServiceImp;



@RestController
@CrossOrigin("*")
@RequestMapping("/api/book")


public class BookController {




	@Autowired
	BookService bookService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public String sayHello(){
        return "lll ";
    }
	
	
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<Book> getBooks() {
		
		
		
		return  bookService.getAllBooks();
	}

	@RequestMapping(value = "/getBook/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Book getBookById(@PathVariable int id) {
		return bookService.getBook(id);
	}
	
	@RequestMapping(value = "/getPrice/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public double getBookPriceById(@PathVariable int id) {
		return bookService.getPriceBook(id);
	}
	@RequestMapping(value = "/updateBook", method = RequestMethod.PUT, headers = "Accept=application/json")
	public Book updateBook(@RequestBody Book book) {
		
		bookService.saveBook(book);
		
		return book;
		
	}
	
	@RequestMapping(value = "/addBook", method = RequestMethod.POST, headers = "Accept=application/json")
	public Book addBook(@RequestBody Book book) {
		
		
		book.setId(0);
		
		bookService.saveBook(book);
		
		return book;
	}

	

	@RequestMapping(value = "/deleteBook/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public String deleteBook(@PathVariable("id") int id) {
		bookService.deleteBook(id);
		 return "Delete Done";

	}	
}

