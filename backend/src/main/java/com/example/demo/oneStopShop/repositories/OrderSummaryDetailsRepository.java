package com.example.demo.oneStopShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.oneStopShop.entities.OrderSummaryDetails;

public interface OrderSummaryDetailsRepository extends JpaRepository<OrderSummaryDetails, Integer>  {

}
