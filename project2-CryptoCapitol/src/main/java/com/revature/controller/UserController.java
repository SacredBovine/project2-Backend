package com.revature.controller;


import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;


import com.revature.service.*;
import com.revature.service.UserService;

import com.revature.models.Asset;

import com.revature.models.Portfolio;

import com.revature.models.User;
import com.revature.models.UserDTO;

@RestController
@CrossOrigin(origins ="http://localhost:4200")
@SessionScope
@RequestMapping(value="/user")
public class UserController {
	
	private HttpSession httpSession;
	private UserService userService;
	private LoginService loginService;
	private PortfolioService portfolioService;
	private User user;
	private UserDTO userDto;
  private UserService userService;
	
	@Autowired
	public UserController(
		User user,
		UserDTO userDto,
		UserService userService, 
		LoginService loginService, 
		HttpSession httpSession,
		PortfolioService portfolioService) 
	{
		this.userDto = userDto;
		this.user = user;
		this.userService = userService;
		this.loginService = loginService;
		this.httpSession = httpSession;
		this.portfolioService = portfolioService;
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
	 public ResponseEntity login(@RequestBody UserDTO userDto){
		//System.out.println(this.httpSession.getAttribute("user"));
		userDto = loginService.login(userDto);
		if (userDto != null) {
			return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	 }
	 
	 @GetMapping("/login")
	 public ResponseEntity login(){
		 this.httpSession.invalidate();
		 return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
	
	 }
	 
/*	 private String getJWTToken(String userName) {
			String key = "AstonVanquish";
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils
					.commaSeparatedStringToAuthorityList("ROLE_USER");
			
			String token = Jwts
					.builder()
					.setId("softtekJWT")
					.setSubject(userName)
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + 300000))
					.signWith(SignatureAlgorithm.HS512,
							secretKey.getBytes()).compact();

			return "Bearer " + token;
		}*/
	
}
