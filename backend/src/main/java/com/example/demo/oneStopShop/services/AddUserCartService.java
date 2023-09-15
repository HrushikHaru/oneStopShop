package com.example.demo.oneStopShop.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.example.demo.oneStopShop.dtos.AddProductToCart;
import com.example.demo.oneStopShop.dtos.CartOverallDetails;
import com.example.demo.oneStopShop.dtos.GetUserCartProductsDto;
import com.example.demo.oneStopShop.entities.AddUserCart;
import com.example.demo.oneStopShop.entities.UserDetailsList;
import com.example.demo.oneStopShop.exceptions.CartItemNotFoundException;
import com.example.demo.oneStopShop.repositories.AddUserCartRepository;

@Service
public class AddUserCartService {
	
	private final AddUserCartRepository addUserRepo;
	
	private final JdbcTemplate jdbcTemp;
	
	private final ProductsService productsServ;
	
	public AddUserCartService(AddUserCartRepository addUserRepo, JdbcTemplate jdbcTemp, ProductsService productsServ) {
		this.addUserRepo = addUserRepo;
		this.jdbcTemp = jdbcTemp;
		this.productsServ = productsServ;
	}
	
	public void addToUserCart(AddProductToCart addToCart) {
		
		AddUserCart addProduct = new AddUserCart();
		
		addProduct.setProductId(addToCart.getProductId());
		addProduct.setNumItems(addToCart.getNumItems());
		addProduct.setSizeSelected(addToCart.getSizeSelected());
		
		UserDetailsList user = new UserDetailsList();
		user.setUserId(addToCart.getUserId());
		
		addProduct.setUserDetailsList(user);
		
		addUserRepo.save(addProduct);
		
	}

	public List<GetUserCartProductsDto> getUserCart(int userId, String imgPath) {
		
		 String query = "select product_id, num_items, size_selected, cart_id from add_user_cart where user_id = ?";
		 
		 List<GetUserCartProductsDto> cartProducts = jdbcTemp.query(query, (ps)->ps.setInt(1, userId), (resultSet, rowNum)->{
		 
			 GetUserCartProductsDto cartProduct = new GetUserCartProductsDto();
			 
			 cartProduct.setProductDetails(productsServ.getParticularProduct(resultSet.getInt(1), imgPath));
			 
			 cartProduct.setNumItems(resultSet.getInt(2));
			 
			 cartProduct.setSizeSelected(resultSet.getString(3));
			 
			 cartProduct.setCartId(resultSet.getInt(4));
			 
			 return cartProduct;
		 });
		
		 return cartProducts;
	}

	public void quantityChange(int cartId, int value) {
		
		Optional<AddUserCart> cartProduct = addUserRepo.findById(cartId);
		
		if(cartProduct.isPresent()) {
			
			AddUserCart considerProduct = cartProduct.get();
			
			considerProduct.setNumItems(value);
			
			addUserRepo.save(considerProduct);
		}
		
	}

	public CartOverallDetails getCartDetails(int userId) {
		
		String query = "select  sum(b.original_price*num_items) as itemsCost, sum((b.original_price - b.discounted_price)*num_items) as discountedPrice, \r\n"
				+ "sum(b.discounted_price*num_items) as totalCost from add_user_cart a left join products b \r\n"
				+ "ON(a.product_id = b.product_id) where a.user_id = ?";
		
		return jdbcTemp.queryForObject(query, new Object[] {userId}, new CartDetailsRowMapper());
		
	}
	
	private static class CartDetailsRowMapper implements RowMapper<CartOverallDetails> {
        @Override
        public CartOverallDetails mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            CartOverallDetails cartDetails = new CartOverallDetails();
            cartDetails.setItemsCost((int)resultSet.getDouble("itemsCost"));
            cartDetails.setDiscountedPrice((int)resultSet.getDouble("discountedPrice"));
            cartDetails.setTotalCost((int)resultSet.getDouble("totalCost"));

            return cartDetails;
        }
    }

	public void removeCartItem(int cartId) {
		
		Optional<AddUserCart> cartItem = addUserRepo.findById(cartId);
		
		if(cartItem.isEmpty()) throw new CartItemNotFoundException("The specified item was not found");
		
		System.out.println(cartItem.get());
		addUserRepo.delete(cartItem.get());
		
	}

}
