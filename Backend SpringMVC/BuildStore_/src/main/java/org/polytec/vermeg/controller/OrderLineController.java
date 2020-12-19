package org.polytec.vermeg.controller;


import java.util.List;

import org.polytec.vermeg.model.Order;
import org.polytec.vermeg.model.OrderLine;
import org.polytec.vermeg.service.BookService;
import org.polytec.vermeg.service.OrderLineService;
import org.polytec.vermeg.service.OrderLineServiceImp;
import org.polytec.vermeg.service.OrderService;
import org.polytec.vermeg.service.OrderServiceImp;
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




@RestController
@CrossOrigin("*")
@RequestMapping("/api/line")

public class OrderLineController {
	
	@Autowired 
	OrderLineService orderLineService;
	
	@Autowired 
	
	BookService bookService ;
	@Autowired
	
	OrderService orderService ; 
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public String sayHello(){
        return "lll ";
    }
	 
	
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<OrderLine> getLineOrders() {
		
		List<OrderLine> listOfOrders = orderLineService.getOrders();
		
		return listOfOrders;
	}

	@RequestMapping(value = "/getLine/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public OrderLine getOrderById(@PathVariable int id) {
		return orderLineService.getOrderLine(id);
	}

	

	@RequestMapping(value = "/addLine/{idBook}", method = RequestMethod.POST, headers = "Accept=application/json")
	public OrderLine addOrder(@RequestBody OrderLine orderline,@PathVariable int idBook) {
		// get by the last last id Order ,,, 
		//so we must have already a current order in table Orders to add throw it the orderLines
		orderline.setOrders(orderService.getOrder(orderService.getCount()));
		
		// get by id Book 
		orderline.setBook(bookService.getBook(idBook));
		bookService.getBook(idBook).getPrice_unit() ;
		
		//Calculate total  OrderLine
		orderline.setTotal(orderLineService.calculate(bookService.getBook(idBook).getPrice_unit(), orderline.getQuantity_line()));
		// Calculate total of  current Order
		
		orderline.getOrders().setTotal(orderline.getTotal()+orderline.getOrders().getTotal());
	
		//update total in  Order table 
		
		orderline.setId_Line(0);
		
		//save data to orderLine table
		orderLineService.saveOrderLine(orderline );
		
		orderService.saveOrder(orderline.getOrders());
		
		return orderline;
	}
	@RequestMapping(value = "/updateLine", method = RequestMethod.PUT, headers = "Accept=application/json")
	public OrderLine  updateOrder(@RequestBody OrderLine order) {
		
		orderLineService.saveOrderLine(order);;
		
		return order;
		
	}
	


	@RequestMapping(value = "/deleteLine/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public String deleteOrder(@PathVariable("id") int id) {
	
		orderLineService.deleteOrderLine(id);

		 return "Delete Done";

	}	
	
	@RequestMapping(value = "/deleteAllLine", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public String deleteAllLine() {
	
		orderLineService.deleteAllLine();

		 return "Delete Done";

	}
}
