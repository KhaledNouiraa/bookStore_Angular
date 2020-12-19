package org.polytec.vermeg.service;

import java.util.List;

import org.polytec.vermeg.dao.BookDAOImp;
import org.polytec.vermeg.dao.OrderDAOImp;
import org.polytec.vermeg.dao.OrderLineDAO;
import org.polytec.vermeg.dao.OrderLineDAOImp;
import org.polytec.vermeg.model.Order;
import org.polytec.vermeg.model.OrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("orderLineService")
public class OrderLineServiceImp implements OrderLineService{

	@Autowired
	OrderLineDAO lineDao;
	
	@Override
	@Transactional
	public List<OrderLine> getOrders() {
		return lineDao.getOrderLine();
	}
	
	@Override
	@Transactional
	public OrderLine getOrderLine(int id) {
		return lineDao.getLine(id);
	}

	@Override
	@Transactional
	public double calculate (double price ,int qtq) {
		
		return price*qtq; 
	}
	
	@Override
	@Transactional
	public void saveOrderLine(OrderLine order) {

		lineDao.saveOrderLine(order);
	}
	

	
	@Override
	@Transactional
	public void deleteOrderLine(int theId) {
		
		lineDao.deleteLine(theId);
	}
	
	@Override
	@Transactional
	public void deleteAllLine() {
		
		lineDao.deleteAllLine();
	}
}
