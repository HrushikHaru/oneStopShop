package com.example.demo.oneStopShop.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.oneStopShop.dtos.CategoriesDto;
import com.example.demo.oneStopShop.dtos.SendCategoriesAndSubCategoriesDto;
import com.example.demo.oneStopShop.dtos.SendCategoriesDto;
import com.example.demo.oneStopShop.dtos.SendSubCategoryDto;
import com.example.demo.oneStopShop.dtos.SubCategoriesDto;
import com.example.demo.oneStopShop.responses.CategoryAddedResponse;
import com.example.demo.oneStopShop.responses.SubCategoriesAddedResponse;
import com.example.demo.oneStopShop.services.CategoriesService;
import com.example.demo.oneStopShop.services.SubCategoriesService;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {
	
	private final CategoriesService categoriesService;
	
	private final SubCategoriesService subCategoriesService;
	
	public AdminController(CategoriesService categoriesService, SubCategoriesService subCategoriesService) {
		this.categoriesService = categoriesService;
		this.subCategoriesService = subCategoriesService;
	}
	
	@PostMapping(path = "/addCategory")
	public ResponseEntity<CategoryAddedResponse> addCategory(@RequestBody CategoriesDto categories){
		CategoryAddedResponse response = new CategoryAddedResponse();
		try {
			categoriesService.addCategory(categories);
			
			response.setMessage("The category was added successfully.");
			response.setAddedOn(new Date());
			
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
		}
		response.setMessage("The category wasn't added.");
		response.setAddedOn(new Date());
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(path = "/getCategory")
	public ResponseEntity<List<SendCategoriesDto>> getCategories(){
		List<SendCategoriesDto> list = null;
		
		try {
			list = categoriesService.getCategories();
			
			return new ResponseEntity<>(list,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(list,HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping(path = "/addSubCategory")
	public ResponseEntity<SubCategoriesAddedResponse> addSubCategory(@RequestBody SubCategoriesDto subCategory){
		SubCategoriesAddedResponse response = new SubCategoriesAddedResponse();
		
		try {
			subCategoriesService.addSubCategory(subCategory);
			
			response.setMessage("The subCategory was added successfully");
			response.setAddedOn(new Date());
			
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		response.setMessage("The subCategory wasn't added.");
		response.setAddedOn(new Date());
		
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(path = "/getNestedCategories")
	public ResponseEntity<List<SendCategoriesAndSubCategoriesDto>> sendDetailsAboutCategories(){
		List<SendCategoriesAndSubCategoriesDto> result = null;
		
		try {
			result =subCategoriesService.getNestedCategories();
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(path = "/getSubCategory")
	public ResponseEntity<List<SendSubCategoryDto>> sendSubCategories(){
		List<SendSubCategoryDto> subCategories = null;
		
		try {
			subCategories = subCategoriesService.getAllSubCategories();
			
			return new ResponseEntity<>(subCategories,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(subCategories,HttpStatus.BAD_REQUEST);
	}

}