package com.example.demo.oneStopShop.securityConfig;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.oneStopShop.entities.UserDetailsList;
import com.example.demo.oneStopShop.repositories.UserDetailsRepository;

import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserDetailsRepository userDetailsRepo;
	
	public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepo) {
		this.userDetailsRepo = userDetailsRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserDetailsList> userInfo = userDetailsRepo.findByEmail(username);
		
		if(userInfo.isPresent()) {
			UserDetailsList user = userInfo.get();
			
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			
			return new User(user.getEmail(),user.getPassword(),grantedAuthorities);
		}
		
		throw new BadCredentialsException("The username with "+username+" is not found");
	}

}
