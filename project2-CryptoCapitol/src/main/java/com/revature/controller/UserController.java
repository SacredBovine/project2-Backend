package com.revature.controller;

import com.revature.models.User;
import com.revature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	
	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User user){
		userService.addOrUpdateUser(user);
		return ResponseEntity.status(201).build();
	}
	@PutMapping
	public ResponseEntity<User> addUpdate(@RequestBody User user){
		userService.addOrUpdateUser(user);
		return ResponseEntity.status(200).build();
	}
	
}
