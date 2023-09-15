package com.example.demo.oneStopShop.dtos;

public class SendSubCategoryDto {
	
	private int subCategoryId;
	
	private String subCategoryName;
	
	public SendSubCategoryDto(int subCategoryId, String subCategoryName) {
		this.subCategoryId = subCategoryId;
		this.subCategoryName = subCategoryName;
	}

	public SendSubCategoryDto() {
		super();
	}

	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	@Override
	public String toString() {
		return "SendSubCategoryDto [subCategoryId=" + subCategoryId + ", subCategoryName=" + subCategoryName + "]";
	}

}
