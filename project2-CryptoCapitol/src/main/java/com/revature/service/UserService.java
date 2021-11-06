package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.User;
import com.revature.repos.UserDAO;

@Service
public class UserService {
	
	
	private UserDAO userDao;
	
	@Autowired
	public UserService(UserDAO userDao) {
		super();
		this.userDao = userDao;
	}
	
	public List<User> findAllUsers(){
		return userDao.findAll();
	}
	
	public User findById(int id) {
		return userDao.findById(id).get();
	}
	
	public void addOrUpdateUser(User user) {
		userDao.save(user);
	}
	
}
