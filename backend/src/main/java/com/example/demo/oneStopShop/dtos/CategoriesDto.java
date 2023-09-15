package com.example.demo.oneStopShop.dtos;

public class CategoriesDto {
	
	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public CategoriesDto(String categoryName) {
		super();
		this.categoryName = categoryName;
	}

	public CategoriesDto() {
		super();
	}

	@Override
	public String toString() {
		return "CategoriesDto [categoryName=" + categoryName + "]";
	}

}
