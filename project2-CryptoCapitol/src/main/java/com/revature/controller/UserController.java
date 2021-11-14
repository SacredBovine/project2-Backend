package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import com.revature.service.*;
import com.revature.models.*;

@RestController
@CrossOrigin(origins ="http://localhost:4200",allowCredentials="true")
@SessionScope
@RequestMapping(value="/user")
public class UserController {
	
	private HttpSession httpSession;
	private UserService userService;
	private LoginService loginService;

	@Autowired
	public UserController(
		UserService userService, 
		LoginService loginService, 
		HttpSession httpSession
		) 
	{	
		this.userService = userService;
		this.loginService = loginService;
		this.httpSession = httpSession;
  }
	
	@GetMapping
	public List<User> findAllUsers(){

		System.out.println(this.httpSession.getAttribute("user"));
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
		if(this.httpSession.getAttribute("user")!=null) {
			userService.addOrUpdateUser(user);
			return ResponseEntity.status(200).build();
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	 @PostMapping("/login")
	 public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDto){
		userDto = loginService.login(userDto);
		if (userDto != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDto);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	 }
	 
	 @GetMapping("/logout")
	 public ResponseEntity logout(){
		 this.loginService.logout();
		 return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	
	 }
	 	
}
