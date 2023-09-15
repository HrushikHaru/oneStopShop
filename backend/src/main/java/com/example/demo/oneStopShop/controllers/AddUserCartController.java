package com.example.demo.oneStopShop.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.oneStopShop.dtos.AddProductToCart;
import com.example.demo.oneStopShop.dtos.CartOverallDetails;
import com.example.demo.oneStopShop.dtos.GetUserCartProductsDto;
import com.example.demo.oneStopShop.responses.PostProductDetailsResponse;
import com.example.demo.oneStopShop.services.AddUserCartService;

@RestController
public class AddUserCartController {
	
	private final AddUserCartService addCartServ;
	
	@Value(value = "${project.products}")
	private String imgPath;
	
	public AddUserCartController(AddUserCartService addCartServ) {
		this.addCartServ = addCartServ;
	}
	
	@PostMapping(path = "/addToUserCart")
	public ResponseEntity<PostProductDetailsResponse> addToCart(@RequestBody AddProductToCart addToCart) {
		
		System.out.println(addToCart);
		
		PostProductDetailsResponse response = new PostProductDetailsResponse();
		
		try {
			addCartServ.addToUserCart(addToCart);
			
			response.setMessage("The product was added to cart successfully.");
			response.setAddedOn(new Date());
			
			return new ResponseEntity<PostProductDetailsResponse>(response,HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		response.setMessage("The Product wasn't added successfully.");
		response.setAddedOn(new Date());
		
		return new ResponseEntity<PostProductDetailsResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(path = "/getUsersCart/{userId}")
	public ResponseEntity<List<GetUserCartProductsDto>> getCartProducts(@PathVariable int userId) {
		List<GetUserCartProductsDto> cartProducts = null;
		try {
			cartProducts = addCartServ.getUserCart(userId, imgPath);
			
			return new ResponseEntity<List<GetUserCartProductsDto>>(cartProducts, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(cartProducts, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping(path = "/qtyChange/{cartId}/{value}")
	public ResponseEntity<?> updateTheQuantityOfTheProduct(@PathVariable int cartId, @PathVariable int value){
		
		try {
			addCartServ.quantityChange(cartId,value);
			
			return ResponseEntity.noContent().build();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping(path = "/cartDetails/{userId}")
	public ResponseEntity<CartOverallDetails> getCartDetails(@PathVariable int userId) {
		CartOverallDetails cartDetails = null;
		try {
			cartDetails = addCartServ.getCartDetails(userId);
			
			return new ResponseEntity<CartOverallDetails>(cartDetails, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<CartOverallDetails>(cartDetails, HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping(path = "/removeCartItem/{cartId}")
	public ResponseEntity<?> removeCartItemFromUsersCartList(@PathVariable int cartId){
		
		try {
			addCartServ.removeCartItem(cartId);
			
			return ResponseEntity.noContent().build();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.badRequest().build();
	}

}
