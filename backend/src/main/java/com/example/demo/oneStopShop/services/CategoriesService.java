package com.example.demo.oneStopShop.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.oneStopShop.dtos.CategoriesDto;
import com.example.demo.oneStopShop.dtos.SendCategoriesDto;
import com.example.demo.oneStopShop.entities.Categories;
import com.example.demo.oneStopShop.repositories.CategoriesRepository;

@Service
public class CategoriesService {
	
	private final CategoriesRepository categoriesRepo;
	
	public CategoriesService(CategoriesRepository categoriesRepo) {
		this.categoriesRepo = categoriesRepo;
	}

	public void addCategory(CategoriesDto categories) {
		
		String categoryName = categories.getCategoryName();
		
		Categories category = new Categories();
		category.setCategoryName(categoryName);
		
		categoriesRepo.save(category);
		
	}
	
	public List<SendCategoriesDto> getCategories() {
		
		List<Categories> categories = categoriesRepo.findAll();
		
		List<SendCategoriesDto> categoriesGet = new ArrayList<>();
		for(Categories set:categories) {
			
			SendCategoriesDto category = new SendCategoriesDto();
			category.setCategoryId(set.getCategoryId());
			category.setCategoryName(set.getCategoryName());
			
			categoriesGet.add(category);
		}
		
		return categoriesGet;
	}

}
