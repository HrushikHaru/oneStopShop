package com.example.demo.oneStopShop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.oneStopShop.entities.UserAddress;
import com.example.demo.oneStopShop.entities.UserDetailsList;

public interface UserAddressRepository extends JpaRepository<UserAddress, Integer> {
	

}
