package com.example.demo.oneStopShop.dtos;

public class SendCategoriesDto {
	
	private int categoryId;
	
	private String categoryName;

	public SendCategoriesDto(int categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public SendCategoriesDto() {
		super();
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "SendCategoriesDto [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}

}
