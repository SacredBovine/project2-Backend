package com.revature.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Order;
import com.revature.repos.OrderDAO;

@Service
public class OrderService {
	private OrderDAO orderDAO;
	
	@Autowired
	public OrderService(OrderDAO orderDAO) {
		super();
		this.orderDAO = orderDAO;
	}
	
	public List<Order> findAllOrders(){
		return orderDAO.findAll();
	}
	
	public Order findOrderById(int id) {
		return orderDAO.findById(id).get();
	}
	
	public void addOrUpdateOrder(Order order) {
		orderDAO.save(order);
	}
	public void deleteOrder(int id) {
		Order order = findOrderById(id);
		orderDAO.delete(order);
	}
	
}
