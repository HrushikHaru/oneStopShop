package com.example.demo.oneStopShop.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	
	private String title;
	
	private int originalPrice;
	
	private int discountedPrice;
	
	private String color;
	
	private String brand;
	
	private String productImgName;
	
	private String material;
	
	@Column(columnDefinition = "LONGTEXT")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "subCategoryId",referencedColumnName = "subCategoryId")
	private SubCategories subCategory;
	
	@OneToOne
	@JoinColumn(name="sizeId",referencedColumnName = "sizeId")
	private Sizes sizes;

	public Products(int productId, int originalPrice, int discountedPrice, String color, String brand,
			String description, SubCategories subCategory, Sizes sizes,String title, String productImgName, String material) {
		super();
		this.productId = productId;
		this.originalPrice = originalPrice;
		this.discountedPrice = discountedPrice;
		this.color = color;
		this.brand = brand;
		this.description = description;
		this.subCategory = subCategory;
		this.sizes = sizes;
		this.title = title;
		this.productImgName = productImgName;
		this.material = material;
	}

	public Products() {
		super();
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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

	public SubCategories getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategories subCategory) {
		this.subCategory = subCategory;
	}

	public Sizes getSizes() {
		return sizes;
	}

	public void setSizes(Sizes sizes) {
		this.sizes = sizes;
	}

	public String getProductImgName() {
		return productImgName;
	}

	public void setProductImgName(String productImgName) {
		this.productImgName = productImgName;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return "Products [productId=" + productId + ", title=" + title + ", originalPrice=" + originalPrice
				+ ", discountedPrice=" + discountedPrice + ", color=" + color + ", brand=" + brand + ", productImgName="
				+ productImgName + ", material=" + material + ", description=" + description + ", subCategory="
				+ subCategory + ", sizes=" + sizes + "]";
	}

}
