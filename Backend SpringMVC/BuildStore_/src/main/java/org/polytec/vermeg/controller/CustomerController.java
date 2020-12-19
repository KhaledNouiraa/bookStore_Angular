package org.polytec.vermeg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.polytec.vermeg.model.Book;

import org.polytec.vermeg.model.Customer;
import org.polytec.vermeg.service.BookServiceImp;
import org.polytec.vermeg.service.CustomerService;
import org.polytec.vermeg.service.CustomerServiceImp;



@RestController
@RequestMapping("/api/Customer")


public class CustomerController {




	@Autowired
	CustomerService custService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public String sayHello(){
        return "lll ";
    }
	
	
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<Customer> getCustomers() {
		
		List<Customer> listOfCust = custService.getAllCustomers();
		
		return listOfCust;
	}

	@RequestMapping(value = "/getCustomer/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Customer getCustById(@PathVariable int id) {
		return custService.getCustomer(id);
	}

	


	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST, headers = "Accept=application/json")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		
	
		theCustomer.setId(0);
		
		custService.saveCustomer(theCustomer);
		
		return theCustomer;
	}
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST, headers = "Accept=application/json")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		
		custService.saveCustomer(theCustomer);
		
		return theCustomer;
		
	}
	
	
	@RequestMapping(value = "/deleteCustomer/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String deleteCustomer(@PathVariable("id") int id) {
		custService.deleteCustomer(id);
		 return "Delete Done";

	}	
}

