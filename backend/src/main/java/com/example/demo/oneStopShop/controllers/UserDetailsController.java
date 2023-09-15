package com.example.demo.oneStopShop.controllers;

import java.util.Date;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.oneStopShop.dtos.UserDetailsDto;
import com.example.demo.oneStopShop.dtos.UserInfoDto;
import com.example.demo.oneStopShop.entities.UserDetailsList;
import com.example.demo.oneStopShop.repositories.UserDetailsRepository;
import com.example.demo.oneStopShop.responses.UserCreatedResponse;
import com.example.demo.oneStopShop.securityConfig.JwtTokenGenerator;
import com.example.demo.oneStopShop.services.UserDetailsEntityService;

@RestController
public class UserDetailsController {
	
	private final UserDetailsEntityService userServ;
	
	private final UserDetailsRepository userRepo;
	
	private final JwtTokenGenerator generator;
	
	public UserDetailsController(UserDetailsEntityService userServ, UserDetailsRepository userRepo, JwtTokenGenerator generator) {
		this.userServ = userServ;
		this.userRepo = userRepo;
		this.generator = generator;
	}

	@PostMapping(path = "/signUp")
	public ResponseEntity<UserCreatedResponse> createNewUser(@RequestBody UserDetailsDto userInfo) {
		
		UserCreatedResponse response = new UserCreatedResponse();
		
		try {
			userServ.addNewUser(userInfo);
			response.setMessage("User with "+userInfo.getEmail()+" has been created.");
			response.setCreatedOn(new Date());
			
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		response.setMessage("The user was not created. Please try again later.");
		response.setCreatedOn(new Date());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/signIn")
	public ResponseEntity<UserInfoDto> checkForUser(Authentication auth){
		UserInfoDto tokenDto = null;
		
		try {
			Optional<UserDetailsList> user = userRepo.findByEmail(auth.getName());
			
			if(user.isPresent()) {
				String firstName = user.get().getFirstName();
				
				String token = generator.receiveToken();
				
				String role = user.get().getRole();
				
				int userId = user.get().getUserId();
				
				UserInfoDto userInfo = new UserInfoDto();
				
				userInfo.setFirstName(firstName);
				userInfo.setToken(token);
				userInfo.setRole(role);
				userInfo.setUserId(userId);
				
				return new ResponseEntity<UserInfoDto>(userInfo,HttpStatus.OK);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(tokenDto,HttpStatus.BAD_REQUEST);
	}
}
