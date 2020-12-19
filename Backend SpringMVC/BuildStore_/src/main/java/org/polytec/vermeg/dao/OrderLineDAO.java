package org.polytec.vermeg.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.polytec.vermeg.model.OrderLine;

public interface OrderLineDAO {

	public OrderLine getLine(int id) ;
	
	public List<OrderLine> getOrderLine() ;
	
	public void setSessionFactory(SessionFactory sf) ;
	
	public void deleteLine(int theId);

	public void saveOrderLine (OrderLine orderLine) ;
	public void deleteAllLine() ;
}
