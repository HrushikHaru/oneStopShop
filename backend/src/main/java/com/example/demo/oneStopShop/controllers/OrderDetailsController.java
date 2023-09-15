package com.example.demo.oneStopShop.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.oneStopShop.dtos.DeleteFromCartDto;
import com.example.demo.oneStopShop.dtos.GetOrderDetails;
import com.example.demo.oneStopShop.services.OrderDetailsService;

@RestController
public class OrderDetailsController {
	
	private final OrderDetailsService orderServ;
	
	public OrderDetailsController(OrderDetailsService orderServ) {
		this.orderServ = orderServ;
	}
	
	@PostMapping(path = "getProductsToOrder")
	public ResponseEntity<?> getTheCartDetailsInOrderSummary(@RequestBody List<DeleteFromCartDto> cartDto) {
		try {
			orderServ.pushCartItemsToOrderSummary(cartDto);
			
			return ResponseEntity.created(null).build();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping(path = "/getOrdersPlaced/{userId}")
	public ResponseEntity<List<GetOrderDetails>>  getTheOrdersForTheUser(@PathVariable int userId) {
		List<GetOrderDetails> orderDetails = null;
		
		try {
			orderDetails = orderServ.getOrders(userId);
			
			return new ResponseEntity<List<GetOrderDetails>>(orderDetails, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<GetOrderDetails>>(orderDetails, HttpStatus.BAD_REQUEST);
	}

}