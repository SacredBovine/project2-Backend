package com.revature.service;

import com.revature.models.UserDTO;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.User;
import com.revature.repos.UserDAO;
import com.revature.utils.PassEncrypt;

@Service
public class LoginService {

	private UserDAO userDao;
	public HttpSession httpSession;
	
	@Autowired
	public LoginService(UserDAO userDao, HttpSession httpSession) {
		super();
		this.userDao = userDao;
		this.httpSession = httpSession;
	}
	
	public UserDTO login(UserDTO userDto) {
		User user = userDao.findByUserName(userDto.getUserName());	
		if(user!=null && (PassEncrypt.getHash(userDto.getPassword().getBytes(), "SHA-512").equals(String.valueOf(user.getPassword())))) {
			
			
			
			userDto.setUserId(user.getUserId());
			userDto.setFirstName(user.getFirstName());
			userDto.setLastName(user.getLastName());
			userDto.setPassword(null);
			userDto.setEmail(user.getEmail());
			this.httpSession.setMaxInactiveInterval(300);
			this.httpSession.setAttribute("user", user);
			return userDto;
		}
		return null;
	}
	
	public void logout() {
		this.httpSession.removeAttribute("user");
		this.httpSession.removeAttribute("portfolio");
		this.httpSession.invalidate();
	}
}
