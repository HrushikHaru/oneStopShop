package com.example.demo.oneStopShop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.oneStopShop.dtos.WishListDetailsDto;
import com.example.demo.oneStopShop.dtos.WishListDto;
import com.example.demo.oneStopShop.entities.UserDetailsList;
import com.example.demo.oneStopShop.entities.WishList;
import com.example.demo.oneStopShop.exceptions.UserNotFoundException;
import com.example.demo.oneStopShop.repositories.UserDetailsRepository;
import com.example.demo.oneStopShop.repositories.WishListRepository;

@Service
public class WishListService {
	
	private final WishListRepository wishListRepo;
	
	private final UserDetailsRepository userRepo;
	
	private final JdbcTemplate jdbcTemp;
	
	private ProductsService productServ;
	
	public WishListService(WishListRepository wishListRepo, UserDetailsRepository userRepo, JdbcTemplate jdbcTemp, ProductsService productServ) {
		this.wishListRepo = wishListRepo;
		this.userRepo = userRepo;
		this.jdbcTemp = jdbcTemp;
		this.productServ = productServ;
	}

	public void addToWishList(WishListDto wishListDto) {
		
		Optional<UserDetailsList> user= userRepo.findById(wishListDto.getUserId());
		
		if(user.isEmpty()) throw new UserNotFoundException("The user does not exist.");
		
		WishList wishList = new WishList();
		
		wishList.setProductId(wishListDto.getProductId());
		
		UserDetailsList userDetailsList = new UserDetailsList();
		
		userDetailsList.setUserId(wishListDto.getUserId());
		
		wishList.setUserDetailsList(userDetailsList);
		
		wishListRepo.save(wishList);
		
	}

	public void removeFromWishList(int userId, int productId) {
		
		Optional<UserDetailsList> user = userRepo.findById(userId);
		
		if(user.isEmpty()) throw new UserNotFoundException("The User does not exist.");
		
		Optional<WishList> wishListToDelete = wishListRepo.findByProductIdAndUserDetailsList(productId, user.get());
		
		wishListRepo.delete(wishListToDelete.get());
		
	}

	public List<Integer> getWishList(int userId) {
		
		Optional<UserDetailsList> user = userRepo.findById(userId);
		
		if(user.isEmpty()) throw new UserNotFoundException("The User does not exist.");
		
		String query = "select b.product_id from user_details_list a left join wish_list b ON(a.user_id = b.user_id) where a.user_id = ?";
		
		List<Integer> list = jdbcTemp.query(query, (ps)->ps.setInt(1, userId), (resultSet, rowNum)->{
			return resultSet.getInt(1);
		});
		
		return list;
	}

	public List<WishListDetailsDto> getDetailsOfWishList(int userId, String imgValue) {
		
		Optional<UserDetailsList> user = userRepo.findById(userId);
		
		if(user.isEmpty()) throw new UserNotFoundException("The User does not exist.");
		
		String query = "select a.product_id, a.user_id, b.brand, b.discounted_price, b.original_price, \r\n"
				+ "b.product_img_name, b.title, c.sub_category_name, d.category_name \r\n"
				+ "from wish_list a left join products b ON(a.product_id = b.product_id) left join \r\n"
				+ "sub_categories c ON(b.sub_category_id = c.sub_category_id) left join\r\n"
				+ "categories d ON(c.category_id = d.category_id) where a.user_id = ?";
		
		List<WishListDetailsDto> productDetails =jdbcTemp.query(query, (ps)->ps.setInt(1, userId), (resultSet, rowNum)->{
			
			WishListDetailsDto details = new WishListDetailsDto();
			
			details.setProductId(resultSet.getInt(1));
			details.setUserId(resultSet.getInt(2));
			details.setBrand(resultSet.getString(3));
			details.setDiscountedPrice(resultSet.getInt(4));
			details.setOriginalPrice(resultSet.getInt(5));
			
			String base64Encoded = productServ.convertToBase64EncodedImage(imgValue, resultSet.getString(6));
			details.setBase64EncodedProductImg(base64Encoded);
			
			details.setTitle(resultSet.getString(7));
			details.setSubCategoryName(resultSet.getString(8));
			details.setCategoryName(resultSet.getString(9));
			
			return details;
		});
		
		return productDetails;
	}

}
