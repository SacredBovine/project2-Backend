package com.revature.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.models.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer>{
	
	@Query("SELECT user FROM User user WHERE user.email = ?1")
	public User findByEmail(String email);
}
