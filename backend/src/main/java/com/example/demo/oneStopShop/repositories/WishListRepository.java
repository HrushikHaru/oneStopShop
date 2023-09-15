package com.example.demo.oneStopShop.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.oneStopShop.entities.UserDetailsList;
import com.example.demo.oneStopShop.entities.WishList;

public interface WishListRepository extends JpaRepository<WishList, Integer> {
	
	Optional<WishList> findByProductIdAndUserDetailsList(int product_id, UserDetailsList user_details_list);

}
