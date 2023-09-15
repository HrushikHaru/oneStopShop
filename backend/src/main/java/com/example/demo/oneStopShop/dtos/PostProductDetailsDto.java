package com.example.demo.oneStopShop.dtos;

public class PostProductDetailsDto {
	
	private String title;
	
	private int originalPrice;
	
	private int discountedPrice;
	
	private String color;
	
	private String brand;
	
	private String description;
	
	private int sizeS;
	
	private int sizeM;
	
	private int sizeL;
	
	private String productImgName;
	
	private int subCategoryId;
	
	private String material;

	public PostProductDetailsDto(String title, int originalPrice, int discountedPrice, String color, String brand,
			String description, int sizeS, int sizeM, int sizeL, String productImgName, int subCategoryId,
			String material) {
		super();
		this.title = title;
		this.originalPrice = originalPrice;
		this.discountedPrice = discountedPrice;
		this.color = color;
		this.brand = brand;
		this.description = description;
		this.sizeS = sizeS;
		this.sizeM = sizeM;
		this.sizeL = sizeL;
		this.productImgName = productImgName;
		this.subCategoryId = subCategoryId;
		this.material = material;
	}

	public PostProductDetailsDto() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(int originalPrice) {
		this.originalPrice = originalPrice;
	}

	public int getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(int discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSizeS() {
		return sizeS;
	}

	public void setSizeS(int sizeS) {
		this.sizeS = sizeS;
	}

	public int getSizeM() {
		return sizeM;
	}

	public void setSizeM(int sizeM) {
		this.sizeM = sizeM;
	}

	public int getSizeL() {
		return sizeL;
	}

	public void setSizeL(int sizeL) {
		this.sizeL = sizeL;
	}

	public String getProductImgName() {
		return productImgName;
	}

	public void setProductImgName(String productImgName) {
		this.productImgName = productImgName;
	}

	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return "PostProductDetailsDto [title=" + title + ", originalPrice=" + originalPrice + ", discountedPrice="
				+ discountedPrice + ", color=" + color + ", brand=" + brand + ", description=" + description
				+ ", sizeS=" + sizeS + ", sizeM=" + sizeM + ", sizeL=" + sizeL + ", productImgName=" + productImgName
				+ ", subCategoryId=" + subCategoryId + ", material=" + material + "]";
	}

}
