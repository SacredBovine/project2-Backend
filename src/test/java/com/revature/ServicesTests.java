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
public class ServicesTests {

	@InjectMocks
	UserService userService;
	@Mock
	UserDAO userDao;
	
    @Test
    public void testFindAllUser()
    {
        List<User> list = new ArrayList<User>();
        User user1 = new User(1,"can123","Pass1@","Can","Nguyen","can@revature.net");
        User user2 = new User(2,"steven123","Pass2@","Steven","Nguyen","steven@revature.net");    
        list.add(user1);
        list.add(user2); 
        when(userDao.findAll()).thenReturn(list);
        //test
        List<User> uList = userService.findAllUsers();
        assertEquals(2, uList.size());
        verify(userDao, times(1)).findAll();
    }
    
    @Test
    public void testFindUserById(){
    	
    	Optional<User> user1 = Optional.of(new User(1,"can123","Pass1@","Can","Nguyen",
    			"can@revature.net"));
    	when(userDao.findById(1)).thenReturn(user1);  	
    	User user = userService.findById(1);
        assertEquals(user1.get(), user);
        verify(userDao, times(1)).findById(1);
    }
    
    @Test
    public void testaddUser() {
    	
    	User user = new User(1,"can123","Pass1@","Can","Nguyen","can@revature.net");
        User hashedPassUser = new User(user.getUserName(), 
        		PassEncrypt.getHash(user.getPassword().getBytes(), "SHA-512"),
        		user.getFirstName(), user.getLastName(), user.getEmail()); 	
        userService.addUser(user); 
        verify(userDao,times(1)).save(hashedPassUser);    	
    }
    
    @Test
    public void testUpdateUser() {
    	
    	UserDTO userDto = new UserDTO(1,"Can","Nguyen","can@revature.net","can123");
        Optional<User> hashedPassUser = Optional.of(new User(1,userDto.getUserName(), 
        		PassEncrypt.getHash("Pass1@".getBytes(), "SHA-512"),
        		userDto.getFirstName(), userDto.getLastName(), userDto.getEmail())); 
        when(userDao.findById(userDto.getUserId())).thenReturn(hashedPassUser); 
        userService.updateUser(userDto); 
        verify(userDao,times(1)).save(hashedPassUser.get());    	
    }
    
    @Test
    public void testFindByUserName(){
    	
    	User user1 = new User(1,"can123","Pass1@","Can","Nguyen","can@revature.net");
    	when(userDao.findByUserName("can123")).thenReturn(user1);   	
    	User user = userService.findByUserName("can123");
        assertEquals(user1, user);
        verify(userDao, times(1)).findByUserName("can123"); 	
    }
    
	@InjectMocks
	AssetService assetService;
	@Mock
	AssetDAO assetDAO;
	
    @Test
    public void testFindAllAsset(){
    	
        List<Asset> list = new ArrayList<Asset>();
        Asset asset1 = new Asset(1,"Bitcoin","BTC",1);
        Asset asset2 = new Asset(2,"Ethereum","ETH",2);  
        list.add(asset1);
        list.add(asset2);
        when(assetDAO.findAll()).thenReturn(list);
        //test
        List<Asset> assList = assetService.findAllAssets();
        assertEquals(2, assList.size());
        verify(assetDAO, times(1)).findAll();
    }
    
    @Test
    public void testFindAssetById(){
    	
    	Optional<Asset> asset1 = Optional.of(new Asset(1,"Bitcoin","BTC",1));
    	when(assetDAO.findById(1)).thenReturn(asset1);
    	Asset asset = assetService.findAssetById(1);
        assertEquals(asset1.get(), asset);
        verify(assetDAO, times(1)).findById(1);
    }
    
    @Test
    public void testaddOrUpdateAsset() {
    	
    	Asset asset = new Asset(1,"Bitcoin","BTC",1);
    	assetService.addOrUpdateAsset(asset);
        verify(assetDAO,times(1)).save(asset);
    }
    
    @Test
    public void testFindAssetBySymbol(){
    	
    	Optional<Asset> asset1 = Optional.of(new Asset(1,"Bitcoin","BTC",1));
    	when(assetDAO.findBySymbol("BTC")).thenReturn(asset1);   	
    	Optional<Asset> asset = assetService.findBySymbol("BTC");
        assertEquals(asset1, asset);
        verify(assetDAO, times(1)).findBySymbol("BTC"); 	
    }
    
	@InjectMocks
	LoginService loginService;
	@Mock
	HttpSession httpSession;
	
	@Test
    public void testLogin() {
		
    	User user = new User(1,"can123",PassEncrypt.getHash("Pass1@".getBytes(), "SHA-512"),
    			"Can","Nguyen","can@revature.net");
    	when(userDao.findByUserName("can123")).thenReturn(user);
        UserDTO userDTO = new UserDTO("can123","Pass1@");
        UserDTO result = loginService.login(userDTO);
        assertNotNull(result);	
	}
    
	@Test
    public void testLoginFail() {
		
    	User user = new User(1,"can123",PassEncrypt.getHash("Pass1@".getBytes(), "SHA-512"),
    			"Can","Nguyen","can@revature.net");
    	when(userDao.findByUserName("can123")).thenReturn(user);
    	UserDTO result = loginService.login(new UserDTO("can123","Pass1@@"));
    	assertNull(result);	
	}
    
	
    
	
}
