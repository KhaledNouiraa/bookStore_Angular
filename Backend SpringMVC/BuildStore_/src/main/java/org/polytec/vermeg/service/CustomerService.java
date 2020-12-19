package org.polytec.vermeg.service;

import java.util.List;

import org.polytec.vermeg.model.Customer;



public interface CustomerService {
	public List<Customer> getAllCustomers() ;
	
	public Customer getCustomer(int id) ;

	public void saveCustomer(Customer theCustomer) ;

	

	public void deleteCustomer(int theId);
	
}