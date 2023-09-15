package com.example.demo.oneStopShop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.oneStopShop.dtos.DeleteFromCartDto;
import com.example.demo.oneStopShop.dtos.GetOrderDetails;
import com.example.demo.oneStopShop.dtos.OrderProductDetails;
import com.example.demo.oneStopShop.entities.AddUserCart;
import com.example.demo.oneStopShop.entities.OrderSummaryDetails;
import com.example.demo.oneStopShop.entities.Products;
import com.example.demo.oneStopShop.entities.UserDetailsList;
import com.example.demo.oneStopShop.repositories.AddUserCartRepository;
import com.example.demo.oneStopShop.repositories.OrderSummaryDetailsRepository;
import com.example.demo.oneStopShop.repositories.ProductsRepository;

@Service
public class OrderDetailsService {
	
	private final AddUserCartRepository addCartRepo;
	
	private final OrderSummaryDetailsRepository orderSummaryDetailsRepo;
	
	private final ProductsRepository productRepo;
	
	private final JdbcTemplate jdbcTemp;
	
	public OrderDetailsService(AddUserCartRepository addCartRepo, OrderSummaryDetailsRepository orderSummaryDetailsRepo, JdbcTemplate jdbcTemp, ProductsRepository productRepo) {
		this.addCartRepo = addCartRepo;
		this.orderSummaryDetailsRepo = orderSummaryDetailsRepo;
		this.jdbcTemp = jdbcTemp;
		this.productRepo = productRepo;
	}

	@Transactional
	public void pushCartItemsToOrderSummary(List<DeleteFromCartDto> cartDto) {
		
		int userId = cartDto.get(0).getUserId();
		String productId = "";
		String sizeSelected = "";
		String numItems = "";
		
		for(int i=0;i<cartDto.size();i++) {
			DeleteFromCartDto consider = cartDto.get(i);
			
			if(i == cartDto.size()-1) {
				productId += consider.getProductId();
				sizeSelected += consider.getSizeSelected();
				numItems += consider.getNumItems();
			}else {
				productId += consider.getProductId() + ",";
				sizeSelected += consider.getSizeSelected() + ",";
				numItems += consider.getNumItems() + ",";
			}
			
			Optional<AddUserCart> userCart = addCartRepo.findById(consider.getCartId());
			addCartRepo.delete(userCart.get());
		}
		
		OrderSummaryDetails orderDetails = new OrderSummaryDetails();
		orderDetails.setNumItems(numItems);
		orderDetails.setProductId(productId);
		orderDetails.setSizeSelected(sizeSelected);
		orderDetails.setDateTime(cartDto.get(0).getDateTime());
		
		UserDetailsList userDetails = new UserDetailsList();
		userDetails.setUserId(userId);
		
		orderDetails.setUserDetails(userDetails);
		
		orderSummaryDetailsRepo.save(orderDetails);
		
	}

	public List<GetOrderDetails> getOrders(int userId) {
		
		String query = "select num_items, product_id, size_selected, date_time from order_summary_details where user_id = ?";
		
		List<GetOrderDetails> orderDetails = jdbcTemp.query(query, (ps)->ps.setInt(1, userId), (resultSet, rowNum)->{
		
			GetOrderDetails details = new GetOrderDetails();
			
			details.setDateTime(resultSet.getString(4));
			
			String [] productIds = resultSet.getString(2).split(",");
			String [] numItems = resultSet.getString(1).split(",");
			String [] sizeSelected = resultSet.getString(3).split(",");
			
			List<OrderProductDetails> productDetails = new ArrayList<>();
			
			for(int i=0;i<productIds.length;i++) {
				
				OrderProductDetails productDetail = new OrderProductDetails();
				
				Optional<Products> product = productRepo.findById(Integer.parseInt(productIds[i]));
				
				Products productInfo = product.get();
				
				productDetail.setProductId(productInfo.getProductId());
				productDetail.setBrand(productInfo.getBrand());
				productDetail.setColor(productInfo.getColor());
				productDetail.setTitle(productInfo.getTitle());
				
				productDetail.setNumItems(Integer.parseInt(numItems[i]));
				productDetail.setSizeSelected(sizeSelected[i]);
				
				productDetails.add(productDetail);
				
			}
			
			details.setProductDetails(productDetails);
			
			return details;
		});
		
		return orderDetails;
	}

}
