package com.revature.service;

import com.revature.models.User;
import com.revature.repos.UserDAO;
import com.revature.utils.PassEncrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
		User hashedPassUser = new User(user.getUserName(), PassEncrypt.getHash(user.getPassword().getBytes(), "SHA-512"), user.getFirstName(), user.getLastName(), user.getEmail());
		userDao.save(hashedPassUser);
	}
}
