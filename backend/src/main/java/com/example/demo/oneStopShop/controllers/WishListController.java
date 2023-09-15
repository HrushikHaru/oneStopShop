package com.example.demo.oneStopShop.controllers;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.oneStopShop.dtos.WishListDetailsDto;
import com.example.demo.oneStopShop.dtos.WishListDto;
import com.example.demo.oneStopShop.responses.WishListResponse;
import com.example.demo.oneStopShop.services.ProductsService;
import com.example.demo.oneStopShop.services.WishListService;

@RestController
public class WishListController {
	
	private final WishListService wishListServ;
	
	@Value(value = "${project.products}")
	private String imgValue;
	
	public WishListController(WishListService wishListServ) {
		this.wishListServ = wishListServ;
	}
	
	@PostMapping("/addWishListProduct")
	public ResponseEntity<WishListResponse> addProductToWishList(@RequestBody WishListDto wishListDto) {
		
		WishListResponse response = new WishListResponse();
		
		try {
			wishListServ.addToWishList(wishListDto);
			response.setResposne("The product has been added to wishList");
			response.setUpdatedOn(new Date());
			
			return new ResponseEntity<WishListResponse>(response, HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		response.setResposne("The product wasn't added to the wishList. Please try again later");
		response.setUpdatedOn(new Date());
		return new ResponseEntity<WishListResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/removeWishListProduct/{userId}/{productId}")
	public ResponseEntity<WishListResponse> removeProductFromWishList(@PathVariable int userId, @PathVariable int productId){
		
		WishListResponse response = new WishListResponse();
		
		try {
			wishListServ.removeFromWishList(userId, productId);
			response.setResposne("The product was deleted from wish list successfully.");
			response.setUpdatedOn(new Date());
			return new ResponseEntity<WishListResponse>(response, HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		response.setResposne("The product wasn't delete from list.");
		response.setUpdatedOn(new Date());
		return new ResponseEntity<WishListResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(path = "/getWishList/{userId}")
	public ResponseEntity<List<Integer>> getAllWishListedProducts(@PathVariable int userId){
		List<Integer> list = null;
		try {
			list = wishListServ.getWishList(userId);
			
			return new ResponseEntity<List<Integer>>(list, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Integer>>(list, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(path = "/getDetailsAboutWishList/{userId}")
	public ResponseEntity<List<WishListDetailsDto> > getDetailsAboutWishList(@PathVariable int userId){
		List<WishListDetailsDto> productDetails = null;
		
		try {
			productDetails = wishListServ.getDetailsOfWishList(userId, imgValue);
			
			return new ResponseEntity<List<WishListDetailsDto>>(productDetails, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<WishListDetailsDto>>(productDetails, HttpStatus.BAD_REQUEST);
	}

}
