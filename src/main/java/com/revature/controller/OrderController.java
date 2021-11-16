package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Order;
import com.revature.models.OrderFrontEnd;
import com.revature.models.User;
import com.revature.service.AssetService;
import com.revature.service.OrderService;
import com.revature.service.UserService;

@RestController
@RequestMapping(value="/order")
@CrossOrigin(origins ={"http://project-2-crypto-capitol.s3-website.us-east-2.amazonaws.com", "http://localhost:4200"})
public class OrderController {

	private OrderService orderService;
	private UserService userService;
	private AssetService assetService;
	
	@Autowired
	public OrderController(OrderService orderService,UserService userService, AssetService assetService) {
		this.orderService = orderService;
		this.userService = userService;
		this.assetService = assetService;
	}
	
	@GetMapping
	public List<Order> findAllOrders() {
		return orderService.findAllOrders();
	}
	
	@GetMapping("/{id}")
	public Order oneOrder(@PathVariable("id") int id){
		Order order = orderService.findOrderById(id);
		return order;
	}
	
	@PostMapping
	public ResponseEntity<User> addOrder(@RequestBody OrderFrontEnd orderF){
		System.out.println("tao ne:   "+orderF.toString());
		orderService.addOrUpdateOrder(orderF);
		return ResponseEntity.status(200).body(userService.findById(orderF.userId));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Order> deleteOrder(@PathVariable("id") int id){
		orderService.deleteOrder(id);
		return ResponseEntity.status(201).build();
	}
	
}