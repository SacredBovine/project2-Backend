package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.UserService;
import com.revature.models.Asset;
import com.revature.models.User;

@RestController
@RequestMapping(value="/user")
public class UserController {

	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public List<User> findAllUsers(){
		return userService.findAllUsers();
		
	}
	
	@GetMapping("/{id}")
	public User oneUser(@PathVariable("id") int id){
		User user = userService.findById(id);
		return user;
	}
	
	@PostMapping
	public ResponseEntity<List<User>> addUser(@RequestBody User user){
		userService.addOrUpdateUser(user);
		return ResponseEntity.status(201).body(userService.findAllUsers());
	}
	@PutMapping
	public ResponseEntity<User> addUpdate(@RequestBody User user){
		userService.addOrUpdateUser(user);
		return ResponseEntity.status(200).build();
	}
	
}
