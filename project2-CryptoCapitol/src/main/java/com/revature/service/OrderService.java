package com.revature.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Asset;
import com.revature.models.Order;
import com.revature.models.OrderFrontEnd;
import com.revature.models.Portfolio;
import com.revature.models.User;
import com.revature.repos.OrderDAO;



@Service
public class OrderService {
	private OrderDAO orderDAO;
	
	private AssetService assetService;
	private UserService userService;
	private PortfolioService portFolioService;
	
	@Autowired
	public OrderService(OrderDAO orderDAO, AssetService assetService, UserService userService, PortfolioService portFolioService) {
		super();
		this.orderDAO = orderDAO;
		this.assetService = assetService;
		this.userService = userService;
		this.portFolioService = portFolioService;
	}
	
	public List<Order> findAllOrders(){
		return orderDAO.findAll();
	}
	
	public Order findOrderById(int id) {
		return orderDAO.findById(id).get();
	}
	
	public void addOrUpdateOrder(OrderFrontEnd order) {
		
		Optional<Asset> asset = assetService.findBySymbol(order.symbol);
		
		Asset newAsset;
		
		if( !asset.isPresent()  ) {
			newAsset = new Asset(order.name, order.symbol, order.rank);
			assetService.addOrUpdateAsset(newAsset);
			asset = assetService.findBySymbol(order.symbol);
		}else {
			newAsset = asset.get();
		}
		
		User user = userService.findById(order.userId);
		Order newOrder = new Order(order.orderType, newAsset, order.price, order.quantity, user);
		orderDAO.save(newOrder);
		
		List<Portfolio> userPorfolio = portFolioService.findPortfolioByUser(order.userId);
		
		int cont = 0;
		
		for(int i = 0; i < userPorfolio.size(); i++) {
			if(userPorfolio.get(i).getAsset().getSymbol().equals(order.symbol)) {
				Portfolio portfolio = userPorfolio.get(i);
				portfolio.setQuantity(portfolio.getQuantity() + order.quantity);
				portfolio.setInvestment(portfolio.getInvestment() + (order.quantity * order.price ));
				portFolioService.addOrUpdatePortfolio(portfolio);
				cont++;
				break;
			}
		}
		
		if(cont == 0) {
			Portfolio portfolio = new Portfolio(newAsset, user, order.quantity, order.quantity * order.price);
			portFolioService.addOrUpdatePortfolio(portfolio);
		}
		
	}
	public void deleteOrder(int id) {
		Order order = findOrderById(id);
		orderDAO.delete(order);
	}
	
}
