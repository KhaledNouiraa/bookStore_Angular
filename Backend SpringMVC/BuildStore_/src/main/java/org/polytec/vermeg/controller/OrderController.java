package org.polytec.vermeg.controller;


import java.util.List;

import org.polytec.vermeg.model.Order;
import org.polytec.vermeg.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin("*")
@RestController
@RequestMapping("/api/order")

public class OrderController {
	
	@Autowired
	OrderService orderService;
	

	 
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public String sayHello(){
        return "lll ";
    }
	
	
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<Order> getOrders() {
		
		List<Order> listOfOrders = orderService.getOrders();
		
		return listOfOrders;
	}

	@RequestMapping(value = "/getOrder/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Order getOrderById(@PathVariable int id) {
		return orderService.getOrder(id);
	}
	
	@RequestMapping(value = "/getOrderLAstID/", method = RequestMethod.GET, headers = "Accept=application/json")
	public int getOrderNum() {
		return orderService.getCount();
	}

	

	@RequestMapping(value = "/addOrder", method = RequestMethod.POST, headers = "Accept=application/json")
	public Order addOrder(@RequestBody Order order ) {

	
		
		order.setId_orders(0);
		
		orderService.saveOrder(order);
		
		return order;
	}
	@RequestMapping(value = "/updateOrder", method = RequestMethod.POST, headers = "Accept=application/json")
	public Order  updateOrder(@RequestBody Order order) {
		
		orderService.saveOrder(order);
		
		return order;
		
	}
	
	@RequestMapping(value = "/InitOrder", method = RequestMethod.PUT, headers = "Accept=application/json")
	public Order  intializeOrder(@RequestBody Order order) {
		order.setId_orders(orderService.getCount());
		order.setTotal(0.0);
		order.setId_user(1);
		orderService.saveOrder(order);
		
		return order;
		
	}
	
	@RequestMapping(value = "/lastOrder", method = RequestMethod.GET, headers = "Accept=application/json")
	public Order  GettLastOrder() {
		return orderService.getOrder(orderService.getCount());
		
		
	}

	@RequestMapping(value = "/deleteOrder/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public String deleteOrder(@PathVariable("id") int id) {
		orderService.deleteOrder(id);
		 return "Delete Done";

	}	
}
