package com.example.demo.oneStopShop.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.oneStopShop.dtos.UserAddressDto;
import com.example.demo.oneStopShop.dtos.UserAddressDtoSend;
import com.example.demo.oneStopShop.responses.UserCreatedResponse;
import com.example.demo.oneStopShop.services.UserAddressService;

@RestController
public class UserAddressController {
	
	private final UserAddressService userAddressServ;
	
	public UserAddressController(UserAddressService userAddressServ) {
		this.userAddressServ = userAddressServ;
	}

	@PostMapping(path = "/createNewAddress")
	public ResponseEntity<UserCreatedResponse> addAddressForUser(@RequestBody UserAddressDto userAddress) {
		
		UserCreatedResponse response = new UserCreatedResponse();
		try {
			userAddressServ.createNewAddress(userAddress);
			
			response.setMessage("The address was added for the given user.");
			response.setCreatedOn(new Date());
			return new ResponseEntity<UserCreatedResponse>(response, HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		response.setMessage("The address for the user wasn't created");
		response.setCreatedOn(new Date());
		return new ResponseEntity<UserCreatedResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(path = "/getAddress/{userId}")
	public ResponseEntity<List<UserAddressDtoSend>> getUserAddress(@PathVariable int userId){
		
		List<UserAddressDtoSend> address = null;
		
		try {
			address = userAddressServ.getAddress(userId);
			
			return new ResponseEntity<List<UserAddressDtoSend>>(address, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<UserAddressDtoSend>>(address, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(path = "/getParticularAddress/{addressId}")
	public ResponseEntity<UserAddressDtoSend> getParticularAddress(@PathVariable int addressId){
		
		UserAddressDtoSend address = null;
		
		try {
			
			address = userAddressServ.getParticularAddressGet(addressId);
			
			return new ResponseEntity<UserAddressDtoSend>(address, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<UserAddressDtoSend>(address, HttpStatus.BAD_REQUEST);
	}
	
}
