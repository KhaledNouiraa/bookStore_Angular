package org.polytec.vermeg.service;

import java.util.List;

import org.polytec.vermeg.model.Order;

public interface OrderService {
	
	public List<Order> getOrders() ;

	public int getCount();
	
	public Order getOrder(int id)  ;
	
	public void saveOrder(Order order) ;
	
	public void deleteOrder(int theId) ;
	
}
