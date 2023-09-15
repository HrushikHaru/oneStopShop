package com.example.demo.oneStopShop.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.oneStopShop.dtos.UserDetailsDto;
import com.example.demo.oneStopShop.entities.UserDetailsList;
import com.example.demo.oneStopShop.repositories.UserDetailsRepository;

@Service
public class UserDetailsEntityService {
	
	private final UserDetailsRepository userRepo;
	
	private final PasswordEncoder passwordEncoder;
	
	public UserDetailsEntityService(UserDetailsRepository userRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public void addNewUser(UserDetailsDto userInfo) {
		
		UserDetailsList newUser = new UserDetailsList();
		
		newUser.setFirstName(userInfo.getFirstName());
		newUser.setLastName(userInfo.getLastName());
		newUser.setEmail(userInfo.getEmail());
		
		String encodedPassword = passwordEncoder.encode(userInfo.getPassword());
		newUser.setPassword(encodedPassword);
		
		newUser.setRole("User");
		
		userRepo.save(newUser);
		
	}

	
	
}
