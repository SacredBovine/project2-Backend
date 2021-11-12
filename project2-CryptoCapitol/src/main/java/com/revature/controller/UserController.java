package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;


import com.revature.service.*;
import com.revature.models.Portfolio;
import com.revature.models.User;
import com.revature.models.UserDTO;

@RestController
@CrossOrigin
@RequestMapping(value="/user")
public class UserController {
	
	private HttpSession httpSession;
	private UserService userService;
	private LoginService loginService;
	private PortfolioService portfolioService;
	
	@Autowired
	public UserController(UserService userService, 
		LoginService loginService, 
		HttpSession httpSession, 
		PortfolioService portfolioService) 
	{
		this.userService = userService;
		this.loginService = loginService;
		this.httpSession = httpSession;
		this.portfolioService = portfolioService;
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
			 userDto.setEmail(user.getEmail());
		//	 Portfolio portfolio = this.portfolioService.findPortfolioByUser(user.getUserId());
			 this.httpSession.setAttribute("user", user);
			 return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
		 } else {
			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		 }
	 }
	 
/*	 private String getJWTToken(String userName) {
			String key = "AstonVanquish";
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils
					.commaSeparatedStringToAuthorityList("ROLE_USER");
			
			String token = Jwts
					.builder()
					.setId("softtekJWT")
					.setSubject(username)
					.claim("authorities",
							grantedAuthorities.stream()
									.map(GrantedAuthority::getAuthority)
									.collect(Collectors.toList()))
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + 600000))
					.signWith(SignatureAlgorithm.HS512,
							secretKey.getBytes()).compact();

			return "Bearer " + token;
		}*/
	
}
