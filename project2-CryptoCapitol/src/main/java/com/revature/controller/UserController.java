package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.LoginService;
import com.revature.service.UserService;
import com.revature.models.User;
import com.revature.models.UserDTO;

@RestController
@RequestMapping(value="/user")
public class UserController {

	private HttpSession httpSession;
	private UserService userService;
	private LoginService loginService;
	
	@Autowired
	public UserController(UserService userService, LoginService loginService, HttpSession httpSession) {
		this.userService = userService;
		this.loginService = loginService;
		this.httpSession = httpSession;
		
	}
	
	@GetMapping
	public List<User> findAllUsers(){
		System.out.println("getall");
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
	
	 @PostMapping("/login")
	 public ResponseEntity login(@RequestBody UserDTO userDto){
		 User user = loginService.login(userDto);
		 if (user != null) {
			 userDto.setUserId(user.getUserId());
			 userDto.setFirstName(user.getFirstName());
			 userDto.setLastName(user.getLastName());
			 userDto.setPassword(null);
			 this.httpSession.setAttribute("user", user);
			 return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
		 } else {
			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		 }
	 }
	
}
