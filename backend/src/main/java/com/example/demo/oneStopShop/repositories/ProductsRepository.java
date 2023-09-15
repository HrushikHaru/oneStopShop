package com.example.demo.oneStopShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.oneStopShop.entities.Products;

public interface ProductsRepository extends JpaRepository<Products, Integer> {

}
