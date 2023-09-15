package com.example.demo.oneStopShop.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.oneStopShop.dtos.SendCategoriesAndSubCategoriesDto;
import com.example.demo.oneStopShop.dtos.SendSubCategoryDto;
import com.example.demo.oneStopShop.dtos.SubCategoriesDto;
import com.example.demo.oneStopShop.entities.Categories;
import com.example.demo.oneStopShop.entities.SubCategories;
import com.example.demo.oneStopShop.repositories.SubCategoriesRepository;

@Service
public class SubCategoriesService {
	
	private final SubCategoriesRepository subCategoriesRepo;
	
	private final JdbcTemplate jdbcTemp;
	
	public SubCategoriesService(SubCategoriesRepository subCategoriesRepo,JdbcTemplate jdbcTemp) {
		this.subCategoriesRepo = subCategoriesRepo;
		this.jdbcTemp = jdbcTemp;
	}

	public void addSubCategory(SubCategoriesDto subCategory) {
		
		SubCategories newSubCategory = new SubCategories();
		
		newSubCategory.setSubCategoryName(subCategory.getSubCategoryName());
		Categories category = new Categories();
		category.setCategoryId(subCategory.getCategoryId());
		newSubCategory.setCategories(category);
		
		subCategoriesRepo.save(newSubCategory);
	}

	public List<SendCategoriesAndSubCategoriesDto> getNestedCategories() {
		
		String query = "select a.category_name,group_concat(b.sub_category_name) from categories a left join sub_categories b ON(a.category_id = b.category_id) \r\n"
				+ "group by a.category_name";
		
		List<SendCategoriesAndSubCategoriesDto> result = jdbcTemp.query(query, (resultSet,rowNum)->{
		
			SendCategoriesAndSubCategoriesDto dto = new SendCategoriesAndSubCategoriesDto();
			
			dto.setCategory(resultSet.getString(1));
			
			if(resultSet.getString(2) != null) {
				String [] subCategory = resultSet.getString(2).split(",");
				
				List<String> subCategories = new ArrayList<>();
				
				for(int i=0;i<subCategory.length;i++) {
					subCategories.add(subCategory[i]);
				}
				
				dto.setSubCategory(subCategories);
			}
			
			
			return dto;
		});
		
		return result;
		
	}

	public List<SendSubCategoryDto> getAllSubCategories() {
		
		List<SendSubCategoryDto> result = new ArrayList<>();
		
		List<SubCategories> subCategories = subCategoriesRepo.findAll();
		
		for(SubCategories sub:subCategories) {
			SendSubCategoryDto subCat = new SendSubCategoryDto();
			
			subCat.setSubCategoryName(sub.getSubCategoryName());
			subCat.setSubCategoryId(sub.getSubCategoryId());
			
			result.add(subCat);
		}
		
		return result;
		
	}

}
