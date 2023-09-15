package com.example.demo.oneStopShop.dtos;

import java.util.List;

public class SendCategoriesAndSubCategoriesDto {
	
	private String category;
	
	private List<String> subCategory;

	public SendCategoriesAndSubCategoriesDto(String category, List<String> subCategory) {
		super();
		this.category = category;
		this.subCategory = subCategory;
	}

	public SendCategoriesAndSubCategoriesDto() {
		super();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<String> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(List<String> subCategory) {
		this.subCategory = subCategory;
	}

	@Override
	public String toString() {
		return "SendCategoriesAndSubCategoriesDto [category=" + category + ", subCategory=" + subCategory + "]";
	}

}
