package com.example.demo.oneStopShop.dtos;

public class GetAllProductsDto {
	
	private int productId;
	
	private String brand;
	
	private String color;
	
	private String description;
	
	private int discountedPrice;
	
	private int originalPrice;
	
	private String productTitle;
	
	private int sizeL;
	
	private int sizeM;
	
	private int sizeS;
	
	private String categoryName;
	
	private String subCategoryName;
	
	private String material;
	
	private String base64EncodedProductImg;

	public GetAllProductsDto(int productId, String brand, String color, String description, int discountedPrice,
			int originalPrice, String productTitle, int sizeL, int sizeM, int sizeS, String categoryName,
			String subCategoryName, String material, String base64EncodedProductImg) {
		super();
		this.productId = productId;
		this.brand = brand;
		this.color = color;
		this.description = description;
		this.discountedPrice = discountedPrice;
		this.originalPrice = originalPrice;
		this.productTitle = productTitle;
		this.sizeL = sizeL;
		this.sizeM = sizeM;
		this.sizeS = sizeS;
		this.categoryName = categoryName;
		this.subCategoryName = subCategoryName;
		this.material = material;
		this.base64EncodedProductImg = base64EncodedProductImg;
	}

	public GetAllProductsDto() {
		super();
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public int getSizeL() {
		return sizeL;
	}

	public void setSizeL(int sizeL) {
		this.sizeL = sizeL;
	}

	public int getSizeM() {
		return sizeM;
	}

	public void setSizeM(int sizeM) {
		this.sizeM = sizeM;
	}

	public int getSizeS() {
		return sizeS;
	}

	public void setSizeS(int sizeS) {
		this.sizeS = sizeS;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getBase64EncodedProductImg() {
		return base64EncodedProductImg;
	}

	public void setBase64EncodedProductImg(String base64EncodedProductImg) {
		this.base64EncodedProductImg = base64EncodedProductImg;
	}

	@Override
	public String toString() {
		return "GetAllProductsDto [productId=" + productId + ", brand=" + brand + ", color=" + color + ", description="
				+ description + ", discountedPrice=" + discountedPrice + ", originalPrice=" + originalPrice
				+ ", productTitle=" + productTitle + ", sizeL=" + sizeL + ", sizeM=" + sizeM + ", sizeS=" + sizeS
				+ ", categoryName=" + categoryName + ", subCategoryName=" + subCategoryName + ", material=" + material
				+ ", base64EncodedProductImg=" + base64EncodedProductImg + "]";
	}

}
