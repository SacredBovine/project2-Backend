package com.revature;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.models.Asset;
import com.revature.models.Order;
import com.revature.models.OrderFrontEnd;
import com.revature.models.Portfolio;
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.repos.AssetDAO;
import com.revature.repos.OrderDAO;
import com.revature.repos.PortfolioDAO;
import com.revature.repos.UserDAO;
import com.revature.service.AssetService;
import com.revature.service.LoginService;
import com.revature.service.OrderService;
import com.revature.service.PortfolioService;
import com.revature.service.UserService;
import com.revature.utils.PassEncrypt;


@ExtendWith(MockitoExtension.class)
public class PortfolioTest {
	
	@InjectMocks
	PortfolioService portfolioService;
	@Mock
	PortfolioDAO portfolioDAO;
	@Mock
	UserService userService;
	
	
    @Test
    public void testFindPortfoliorById(){
    	
    	User user = new User(1,"can123",PassEncrypt.getHash("Pass1@".getBytes(), 
    			"SHA-512"),"Can","Nguyen","can@revature.net");
    	Asset asset1 = new Asset(1,"Bitcoin","BTC",1);
    	Optional<Portfolio> portfolio1 = Optional.of(new Portfolio(1,  asset1, user, 0.1, 6000));
    	when(portfolioDAO.findById(1)).thenReturn(portfolio1);
    	Portfolio portfolio = portfolioService.findById(1);
        assertEquals(portfolio1.get(), portfolio);
        verify(portfolioDAO, times(1)).findById(1);
    }
    

	
	
	
//    @Test
//    public void testFindPortfoliorByUser(){
//    	
//    	User user = new User(1,"can123",PassEncrypt.getHash("Pass1@".getBytes(), 
//    			"SHA-512"),"Can","Nguyen","can@revature.net");
//    	Asset asset1 = new Asset(1,"Bitcoin","BTC",1);
//    	Optional<Portfolio> portfolio = Optional.of(new Portfolio(1,  asset1, user, 0.1, 6000));
//    	Optional<List<Portfolio>> list = new ArrayList<>();
//    	list.add(portfolio);
//    	when(portfolioDAO.findByUser(user)).thenReturn(list);
//    	
//    	
//    }
//	
	
	
	
	
	
	
	
	

	
	
	
	
	

}
