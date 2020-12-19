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
import org.polytec.vermeg.model.Customer ;
import org.polytec.vermeg.controller.CustomerController;
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


class CustomerControllerTest {

	@Mock 
	private CustomerService customerService ;
	
	@InjectMocks 
	private CustomerController customerController ;
	
	@Autowired
	private MockMvc mockMvc ;
	
	@BeforeEach
	 public void Setup() {
		 MockitoAnnotations.initMocks(this);	
		
		 mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	 } 
	 


	@Test
	void testGetCustomers() throws Exception {
		List<Customer> customers = new ArrayList<>() ;
	
		customers.add(new Customer("poly 1",1));
		customers.add(new Customer("poly 2",2));
		given (customerService.getAllCustomers()).willReturn((List)customers ) ;
		mockMvc.perform(get("/api/Customer/getAll"))
	 		.andExpect(status().isOk())
	 		.andExpect(jsonPath("$", hasSize(2)))
	 		.andDo(MockMvcResultHandlers.print());
	 										
	}
	
	@Test
	void testGetCustomerById () throws Exception {
		
		int idCustomer = 1 ;
		Customer customer = new Customer("poly 1",1) ;
		when(customerService.getCustomer(idCustomer)).thenReturn(customer);
		mockMvc.perform(get("/api/Customer/getCustomer/1"))
				 .andExpect(status().isOk()) 
				 .andExpect(jsonPath("$.id", is(customer.getId())))
		 .andDo(MockMvcResultHandlers.print());
	}
	

	@Test 
	void testAddCustomer () throws Exception {
		
		

		Customer customer = new  Customer("khaled", "Nouira", "@gmail.com");

	  
	       mockMvc.perform(post("/api/Customer/addCustomer")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(customer)))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	            .andDo(print());
	    }
	
	
	@Test 
	void testUpdateCustomer () throws Exception {
		
		

		Customer customer = new  Customer(5 ,"khaled", "Nouira", "@gmail.com");
	    mockMvc.perform(post("/api/Customer/updateCustomer")
               .contentType(MediaType.APPLICATION_JSON)
               .content(new ObjectMapper().writeValueAsString(customer)))
           .andExpect(status().isOk())
           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
           .andDo(print());
			
		   
}

		@Test
		void testDeleteCustomer() throws Exception {
		    int userId = 1;
		    Customer user = new Customer(userId, "user1@gmail.com", "pwd", "Name");
		    given(customerService.getCustomer(userId)).willReturn(user);
		    doNothing().when(customerService).deleteCustomer(user.getId());
		
		    this.mockMvc.perform(get("/api/Customer/deleteCustomer/{id}", user.getId()))
		            .andExpect(status().isOk())
		            .andDo(print());
		
		}

}
