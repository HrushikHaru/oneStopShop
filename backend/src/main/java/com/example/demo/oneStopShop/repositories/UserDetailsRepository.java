package com.example.demo.oneStopShop.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.oneStopShop.entities.UserDetailsList;

public interface UserDetailsRepository extends JpaRepository<UserDetailsList, Integer> {
	
	Optional<UserDetailsList> findByEmail(String email);

}
