package com.example.demo.oneStopShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.oneStopShop.entities.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Integer> {

}
