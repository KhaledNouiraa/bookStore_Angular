package org.polytec.vermeg.service;

import java.util.List;

import org.polytec.vermeg.model.OrderLine;

public interface OrderLineService {

	
	public List<OrderLine> getOrders() ;
	
	public OrderLine getOrderLine(int id) ;
	
	public double calculate (double price ,int qtq) ;
	
	public void saveOrderLine(OrderLine order) ;

	public void deleteOrderLine(int theId) ;
	public void deleteAllLine() ;
}
