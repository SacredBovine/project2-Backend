package com.revature.service;

import com.revature.models.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.User;
import com.revature.repos.UserDAO;

@Service
public class LoginService {

	private UserDAO userDao;
	
	@Autowired
	public LoginService(UserDAO userDao) {
		super();
		this.userDao = userDao;
	}
		
	public User login(UserDTO userDto) {
		User user = userDao.findByEmail(userDto.getEmail());	
		if(user!=null && (String.valueOf(userDto.getPassword().hashCode()).equals(String.valueOf(user.getPassword())))) {
			return user;
		}
		return null;
	}
	
	public void logout() {
		
	}
}
