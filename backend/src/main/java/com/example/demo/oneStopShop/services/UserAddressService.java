package com.example.demo.oneStopShop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.oneStopShop.dtos.UserAddressDto;
import com.example.demo.oneStopShop.dtos.UserAddressDtoSend;
import com.example.demo.oneStopShop.entities.UserAddress;
import com.example.demo.oneStopShop.entities.UserDetailsList;
import com.example.demo.oneStopShop.exceptions.AddressNotFoundException;
import com.example.demo.oneStopShop.exceptions.UserNotFoundException;
import com.example.demo.oneStopShop.repositories.UserAddressRepository;
import com.example.demo.oneStopShop.repositories.UserDetailsRepository;

@Service
public class UserAddressService {
	
	private final UserAddressRepository userAddressRepo;
	
	private final UserDetailsRepository userRepo;
	
	private final JdbcTemplate jdbcTemp;
	
	public UserAddressService(UserAddressRepository userAddressRepo, UserDetailsRepository userRepo, JdbcTemplate jdbcTemp) {
		this.userAddressRepo = userAddressRepo;
		this.userRepo = userRepo;
		this.jdbcTemp = jdbcTemp;
	}

	public void createNewAddress(UserAddressDto userAddress) {
		
		Optional<UserDetailsList> user = userRepo.findById(userAddress.getUserId());
		
		if(user.isEmpty()) throw new UserNotFoundException("The specified user does not exist");
		
		UserAddress address = new UserAddress();
		
		address.setFirstName(userAddress.getFirstName());
		address.setLastName(userAddress.getLastName());
		address.setAddress(userAddress.getAddress());
		address.setCity(userAddress.getCity());
		address.setState(userAddress.getState());
		address.setPostalCode(userAddress.getPostalCode());
		address.setPhoneNumber(userAddress.getPhoneNumber());
		
		UserDetailsList userDetails = new UserDetailsList();
		userDetails.setUserId(userAddress.getUserId());
		
		address.setUserDetails(userDetails);
		
		userAddressRepo.save(address);
		
	}

	public List<UserAddressDtoSend>  getAddress(int userId) {
		
		Optional<UserDetailsList> user = userRepo.findById(userId);
		
		if(user.isEmpty()) throw new UserNotFoundException("The specified user does not exist");
		
		String query ="select address_id, address, first_name, last_name, city, state, phone_number, postal_code\r\n"
				+ "from user_address where user_id = ?";
		
		List<UserAddressDtoSend> addresses = jdbcTemp.query(query, (ps)->ps.setInt(1, userId), (resultSet, rowNum)->{
			UserAddressDtoSend address = new UserAddressDtoSend();
			
			address.setAddressId(resultSet.getInt(1));
			address.setAddress(resultSet.getString(2));
			address.setFirstName(resultSet.getString(3));
			address.setLastName(resultSet.getString(4));
			address.setCity(resultSet.getString(5));
			address.setState(resultSet.getString(6));
			address.setPhoneNumber(resultSet.getString(7));
			address.setPostalCode(resultSet.getInt(8));
			
			return address;
		});
		
		return addresses;
	}

	public UserAddressDtoSend getParticularAddressGet(int addressId) {
		
		Optional<UserAddress> address = userAddressRepo.findById(addressId);
		
		if(address.isEmpty()) throw new AddressNotFoundException("The specified address does not exist.");
		
		UserAddress realAddress = address.get();
		
		UserAddressDtoSend addressSend = new UserAddressDtoSend();
		addressSend.setAddressId(addressId);
		addressSend.setAddress(realAddress.getAddress());
		addressSend.setCity(realAddress.getCity());
		addressSend.setFirstName(realAddress.getFirstName());
		addressSend.setLastName(realAddress.getLastName());
		addressSend.setPhoneNumber(realAddress.getPhoneNumber());
		addressSend.setPostalCode(realAddress.getPostalCode());
		addressSend.setState(realAddress.getState());
		
		return addressSend;
	}
	
	

}
