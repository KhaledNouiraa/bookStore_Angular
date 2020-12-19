package org.polytec.vermeg.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.polytec.vermeg.model.Customer;

public interface CustomerDAO {

	public void setSessionFactory(SessionFactory sf);
	
	public List<Customer> getCustomers();
	
	public Customer getCustomer(int id) ;
	
	public void saveCustomer(Customer cust);
	
	public void deleteCustomer(int theId) ;
}
