package org.polytec.vermeg.mokito.Test;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
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
import org.polytec.vermeg.controller.OrderController;
import org.polytec.vermeg.controller.OrderLineController;
import org.polytec.vermeg.service.OrderLineService;
import org.polytec.vermeg.service.OrderService;
import org.polytec.vermeg.service.CustomerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;


class OrderControlerTest {


	 
	@Mock 
	private OrderService orderService ;
	@InjectMocks  
	private OrderController orderController ;
	
	@Autowired 
	private MockMvc mockMvc ;
	
	@BeforeEach
	 public void Setup() {
		 MockitoAnnotations.initMocks(this);	
		
		 mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
	 } 
	 

	
	
	@Test
	void testGetOrders () throws Exception {
		  List<Order> orders = new ArrayList<Order>() ;
	
		orders.add(new Order(1,1,1) );
		orders.add(new Order(1,1,1) );
		when(orderService.getOrders()).thenReturn((List)orders);
		mockMvc.perform(get("/api/order/getAll"))
				.andExpect(status().isOk()) 
				 .andExpect(jsonPath("$", hasSize(2))) 
		 .andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	void testGetOrderById () throws Exception {
		  
		int idOrder = 1 ;
		Order order = new Order(1,1,1) ;
		when(orderService.getOrder(idOrder)).thenReturn(order);
		mockMvc.perform(get("/api/order/getOrder/1"))
				.andExpect(status().isOk()) 
				  
		 .andDo(MockMvcResultHandlers.print());
	}

	
	@Test  
	void testAddOrder () throws Exception {
		
		

		Order order = new  Order(1,1,1);

	  
	       mockMvc.perform(post("/api/order/addOrder")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(order)))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	            .andDo(print());
	    }
	
	@Test 
	void testUpdateOrder () throws Exception { 
		
		

		Order order = new  Order();
		   mockMvc.perform(post("/api/order/updateOrder")
	               .contentType(MediaType.APPLICATION_JSON)
	               .content(new ObjectMapper().writeValueAsString(order)))
	           .andExpect(status().isOk())
	           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	           .andDo(print());
				
			   
	}
	
	@Test 
	void shouldDeleteOrder() throws Exception {
	    int idOrder = 1;
	    Order order = new Order(1);
	    given(orderService.getOrder(idOrder)).willReturn(order);
	    doNothing().when(orderService).deleteOrder(order.getId_orders());

	    this.mockMvc.perform(get("/api/order/deleteOrder/{id}", order.getId_user()))
	            .andExpect(status().isOk())
	          .andDo(print());

	}

}
