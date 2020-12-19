package org.polytec.vermeg.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.polytec.vermeg.model.Order;

public interface OrderDAO {


	public void deleteOrder(int theId);
	
	public void saveOrder(Order order) ;
	
	public Order getOrder(int id);
	
	public int getOrderNum() ;
	
	public List<Order> getAllOrders() ;
	
	public void setSessionFactory(SessionFactory sf) ;
}
