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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.polytec.vermeg.model.Book;
import org.polytec.vermeg.model.Customer;
import org.polytec.vermeg.model.OrderLine ;
import org.polytec.vermeg.model.Order;
import org.polytec.vermeg.model.OrderLine;
import org.polytec.vermeg.controller.CustomerController;
import org.polytec.vermeg.controller.OrderLineController;
import org.polytec.vermeg.service.OrderLineService;
import org.polytec.vermeg.service.OrderService;
import org.polytec.vermeg.service.BookService;
import org.polytec.vermeg.service.CustomerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;


class LineControlerTest {

	@Mock 
	private OrderLineService lineService ;
	 
	@Mock 
	private OrderService orderService ;
	@Mock 
	private BookService bookService ;
	
	@InjectMocks  
	private OrderLineController lineController ;
	
	@Autowired 
	private MockMvc mockMvc ;
	
	@BeforeEach
	 public void Setup() {
		 MockitoAnnotations.initMocks(this);	
		
		 mockMvc = MockMvcBuilders.standaloneSetup(lineController).build();
	 } 
	 

	
	@Test
	void testGetLine() throws Exception {
		List<OrderLine> lines = new ArrayList<>() ; 
		lines.add(new OrderLine(1,1));
		lines.add(new OrderLine(1,1));
	when (lineService.getOrders()).thenReturn((List)lines ) ;
	 mockMvc.perform(get("/api/line/getAll"))
     .andExpect(status().isOk())
     .andExpect(jsonPath("$", hasSize(2)))
     .andDo(MockMvcResultHandlers.print());
	 										
	}
	
	
	
	@Test
	void testGetOrderLineById () throws Exception {
		
		int idOrderLine = 1 ;
		OrderLine ol= new OrderLine(1,1) ;
		when(lineService.getOrderLine(idOrderLine)).thenReturn(ol);
		mockMvc.perform(get("/api/line/getLine/1"))
				.andExpect(status().isOk()) 
				.andExpect(jsonPath("$.quantity_line", is(ol.getQuantity_line())))
	            .andExpect(jsonPath("$.total", is(ol.getTotal())))
		 .andDo(MockMvcResultHandlers.print());
	} 
	

	@Test 
	void testAddLineOrder () throws Exception {
		
	  Customer customer = new  Customer(  1 ,"khaled", "Nouira", "@gmail.com");

	  Book book  = new Book(1, "khaled", "test", 10, 10) ;
	  
	  Order order  = new Order(1, 1,1);
	  
	  OrderLine orderLine = new OrderLine(1, 20, book, order) ;
	 orderLine.setTotal( lineService.calculate(book.getPrice_unit(),orderLine.getQuantity_line())) ;
	  
	  given(orderService.getOrder(1)).willReturn(order);
	  given(bookService.getBook(1)).willReturn(book);
	     mockMvc.perform(post("/api/line/addLine/1")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(orderLine)))
	       
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.quantity_line", is(orderLine.getQuantity_line())))
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	            .andDo(print());
	    }
	
	
		@Test 
		void testUpdateLine () throws Exception {
	
	  

			OrderLine line = new  OrderLine(1, 10);
			mockMvc.perform(post("/api/line/updateLine")
               .contentType(MediaType.APPLICATION_JSON)
               .content(new ObjectMapper().writeValueAsString(line)))
           .andExpect(status().isOk())
           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
           .andDo(print());
			
		   
}

		@Test
		void testDeleteLine() throws Exception {
			int LineId = 1;
			OrderLine line = new OrderLine(1, 10)  ;
			given(lineService.getOrderLine(LineId)).willReturn(line);
			doNothing().when(lineService).deleteOrderLine(line.getId_Line());

			mockMvc.perform(get("/api/line/deleteLine/{id}", line.getId_Line()))
            .andExpect(status().isOk())
            .andDo(print());

} 

}
