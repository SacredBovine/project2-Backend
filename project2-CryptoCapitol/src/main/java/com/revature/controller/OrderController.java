package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.revature.models.Order;
import com.revature.models.OrderFrontEnd;
import com.revature.models.User;
import com.revature.service.AssetService;
import com.revature.service.OrderService;
import com.revature.service.UserService;

@RestController
@RequestMapping(value="/order")
@CrossOrigin
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
		int id = orderF.userId;
		assetService.addOrUpdateAsset(orderF.asset);
		Order order = new Order(orderF.asset,userService.findById(id),orderF.quantity);
		orderService.addOrUpdateOrder(order);
		return ResponseEntity.status(200).body(userService.findById(id));
	}
	
	@PutMapping
	public ResponseEntity<List<Order>> updateOrder(@RequestBody Order order){
		System.out.println(order.toString());
		orderService.addOrUpdateOrder(order);
		return ResponseEntity.status(201).body(orderService.findAllOrders());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Order> deleteOrder(@PathVariable("id") int id){
		orderService.deleteOrder(id);
		return ResponseEntity.status(201).build();
	}
	
}