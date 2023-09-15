package com.example.demo.oneStopShop.dtos;

public class WishListDetailsDto {
	
	private int productId;
	
	private int userId;
	
	private String brand;
	
	private int discountedPrice;
	
	private int originalPrice;
	
	private String base64EncodedProductImg;
	
	private String title;
	
	private String subCategoryName;
	
	private String categoryName;

	public WishListDetailsDto(int productId, int userId, String brand, int discountedPrice, int originalPrice,
			String base64EncodedProductImg, String title, String sub_category_name, String category_name) {
		super();
		this.productId = productId;
		this.userId = userId;
		this.brand = brand;
		this.discountedPrice = discountedPrice;
		this.originalPrice = originalPrice;
		this.base64EncodedProductImg = base64EncodedProductImg;
		this.title = title;
		this.subCategoryName = sub_category_name;
		this.categoryName = category_name;
	}

	public WishListDetailsDto() {
		super();
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(int discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public int getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(int originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getBase64EncodedProductImg() {
		return base64EncodedProductImg;
	}

	public void setBase64EncodedProductImg(String base64EncodedProductImg) {
		this.base64EncodedProductImg = base64EncodedProductImg;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "WishListDetailsDto [productId=" + productId + ", userId=" + userId + ", brand=" + brand
				+ ", discountedPrice=" + discountedPrice + ", originalPrice=" + originalPrice
				+ ", base64EncodedProductImg=" + base64EncodedProductImg + ", title=" + title + ", subCategoryName="
				+ subCategoryName + ", categoryName=" + categoryName + "]";
	}
	
}
