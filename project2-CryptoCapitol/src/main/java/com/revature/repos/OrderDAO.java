package com.revature.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Order;

public interface OrderDAO extends JpaRepository<Order,Integer>{

}
