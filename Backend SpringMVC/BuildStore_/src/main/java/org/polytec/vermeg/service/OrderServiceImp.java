package org.polytec.vermeg.service;

import java.util.List;

import org.polytec.vermeg.dao.OrderDAO;
import org.polytec.vermeg.dao.OrderDAOImp;

import org.polytec.vermeg.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("orderService")
public class OrderServiceImp implements OrderService {

	@Autowired
	OrderDAO orderDao;
	
	@Override
	@Transactional
	public List<Order> getOrders() {
		return orderDao.getAllOrders();
	}
	
	@Override
	@Transactional
	public int getCount() {
		
		return  orderDao.getOrderNum() ;
	}
	@Override
	@Transactional
	public Order getOrder(int id) {
		return orderDao.getOrder(id);
	}


	@Override
	@Transactional
	public void saveOrder(Order order) {

		orderDao.saveOrder(order);
	}
	

	@Override
	@Transactional
	public void deleteOrder(int theId) {
		
		orderDao.deleteOrder(theId);
	}
}
