package com.revature.service;

import com.revature.models.UserDTO;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.User;
import com.revature.repos.UserDAO;

@Service
public class LoginService {

	private UserDAO userDao;
	private HttpSession httpSession;
	
	@Autowired
	public LoginService(UserDAO userDao, HttpSession httpSession) {
		super();
		this.userDao = userDao;
		this.httpSession = httpSession;
	}
		
	public User login(UserDTO userDto) {
		User user = userDao.findByUserName(userDto.getUserName());	
		if(user!=null && (String.valueOf(userDto.getPassword().hashCode()).equals(String.valueOf(user.getPassword())))) {
			return user;
		}
		return null;
	}
	
	public void logout() {
		this.httpSession.removeAttribute("user");
		this.httpSession.removeAttribute("portfolio");
		this.httpSession.invalidate();
	}
}
