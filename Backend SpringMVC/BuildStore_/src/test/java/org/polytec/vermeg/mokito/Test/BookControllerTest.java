package org.polytec.vermeg.mokito.Test;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.BDDMockito.* ;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.hibernate.boot.registry.classloading.internal.ClassLoaderServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.polytec.vermeg.model.Book ;
import org.polytec.vermeg.controller.BookController;
import org.polytec.vermeg.controller.CustomerController;
import org.polytec.vermeg.service.BookService;
import org.polytec.vermeg.service.CustomerService;
import org.polytec.vermeg.service.CustomerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;


class BookControllerTest {

	@Mock 
	private BookService bookService ;
	 
	@InjectMocks 
	private BookController bookController ; 
	
	@Autowired
	private MockMvc mockMvc ;
	
	@BeforeEach
	 public void Setup() {
		 MockitoAnnotations.initMocks(this);	
		
		 mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
	 } 
	 


	@Test
	void testGetBooks() throws Exception {
		List<Book> customers = new ArrayList<>() ;
	
		customers.add(new Book(5, "KHALED", "TEST", 10, 10));
		customers.add(new Book(5, "KHALED", "TEST", 10, 10));
	given (bookService.getAllBooks()).willReturn((List)customers ) ;
	 mockMvc.perform(get("/api/book/getAll"))
     .andExpect(status().isOk())
     .andExpect(jsonPath("$", hasSize(2)))
     .andDo(MockMvcResultHandlers.print());
	 										
	}
	 
	@Test
	void testGetBookById () throws Exception {
		 
		int idBook = 1 ;
		Book book = new Book(5, "KHALED", "TEST", 10, 10);
		when(bookService.getBook(idBook)).thenReturn(book);
		mockMvc.perform(get("/api/book/getBook/1"))
				.andExpect(status().isOk()) 
				   .andExpect(jsonPath("$.id", is(book.getId())))
		 .andDo(MockMvcResultHandlers.print());
	}
	
	
	
	

	@Test  
	void testAddBook () throws Exception {
	
		

		Book book = new  Book(5, "KHALED", "TEST", 10, 10);

	  bookService.saveBook(book);
	       mockMvc.perform(post("/api/book/addBook")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(book)))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	            .andDo(print());
	    }
	
	
	@Test 
	void testUpdateBook () throws Exception { 
	
	

		Book book = new  Book();
		mockMvc.perform(post("/api/book/updateBook")
               .contentType(MediaType.APPLICATION_JSON)
               .content(new ObjectMapper().writeValueAsString(book)))
           .andExpect(status().isOk())
           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
           .andDo(print());
			
		   
}

	@Test 
	void testDeleteBook() throws Exception {
		int bookid = 1;
		Book book = new Book();
		given(bookService.getBook(bookid)).willReturn(book);
		doNothing().when(bookService).deleteBook(book.getId());

		mockMvc.perform(get("/api/book/deleteBook/{id}", book.getId()))
            .andExpect(status().isOk())
            .andDo(print());

}

}
