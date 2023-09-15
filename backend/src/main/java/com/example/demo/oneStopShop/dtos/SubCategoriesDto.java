package com.example.demo.oneStopShop.dtos;

public class SubCategoriesDto {
	
	private String subCategoryName;
	
	private int categoryId;

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public SubCategoriesDto(String subCategoryName, int categoryId) {
		super();
		this.subCategoryName = subCategoryName;
		this.categoryId = categoryId;
	}

	public SubCategoriesDto() {
		super();
	}

	@Override
	public String toString() {
		return "SubCategoriesDto [subCategoryName=" + subCategoryName + ", categoryId=" + categoryId + "]";
	}

}
