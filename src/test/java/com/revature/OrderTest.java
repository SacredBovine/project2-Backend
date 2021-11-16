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
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.repos.AssetDAO;
import com.revature.repos.OrderDAO;
import com.revature.repos.UserDAO;
import com.revature.service.AssetService;
import com.revature.service.LoginService;
import com.revature.service.OrderService;
import com.revature.service.PortfolioService;
import com.revature.service.UserService;
import com.revature.utils.PassEncrypt;


@ExtendWith(MockitoExtension.class)
public class OrderTest {
	
	@InjectMocks
	OrderService orderService;
	
	
	@Mock
	PortfolioService portFolioService;
	@Mock
	OrderDAO orderDAO;
	@Mock
	AssetDAO assetDAO;
	@Mock
	AssetService assetService;
	@Mock
	UserService userService;
	
    
    @Test
    public void testFindAllOrder(){
    	
        List<Order> list = new ArrayList<Order>();
        Asset asset1 = new Asset(1,"Bitcoin","BTC",1);
        Asset asset2 = new Asset(2,"Ethereum","ETH",2); 
        User user = new User(1,"can123","Pass1@","Can","Nguyen","can@revature.net");
        Order order1 = new Order(1,  asset1, 60000, 0.1, user);
        Order order2 = new Order(1,  asset2, 6000, 1, user);  
        list.add(order1);
        list.add(order2);
        when(orderDAO.findAll()).thenReturn(list);
        //test
        List<Order> oList = orderService.findAllOrders();
        assertEquals(2, oList.size());
        verify(orderDAO, times(1)).findAll();
    }
    
    @Test
    public void testFindOrderById(){
    	
    	User user = new User(1,"can123","Pass1@","Can","Nguyen","can@revature.net");
    	Asset asset1 = new Asset(1,"Bitcoin","BTC",1);
    	Optional<Order> order1 = Optional.of(new Order(1,  asset1, 60000, 0.1, user));
    	when(orderDAO.findById(1)).thenReturn(order1);
    	Order order = orderService.findOrderById(1);
        assertEquals(order1.get(), order);
        verify(orderDAO, times(1)).findById(1);
    }
    
    @Test
    public void testaddOrUpdateOrder() {
    	
    	User user = new User(1,"can123","Pass1@","Can","Nguyen","can@revature.net");
    	Optional<Asset> asset1 = Optional.of(new Asset(1,"Bitcoin","BTC",1));
    	OrderFrontEnd orderF = new OrderFrontEnd(1,"Bitcoin","BTC",1,1,0.1,60000);
    	Order order = new Order(1,  asset1.get(), 60000, 0.1, user);
    	//when(this.assetDAO.findBySymbol(orderF.symbol)).thenReturn(asset1);
    	when(assetService.findBySymbol(orderF.symbol)).thenReturn(asset1);
    	when(userService.findById(orderF.userId)).thenReturn(user);

    	orderService.addOrUpdateOrder(orderF);
        verify(orderDAO,times(1)).save(order);
    }
    
	

}
