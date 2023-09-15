package com.example.demo.oneStopShop.dtos;

public class OrderProductDetails {
	
	private int productId;
	
	private String title;
	
	private int numItems;
	
	private String sizeSelected;
	
	private String color;
	
	private String brand;

	public OrderProductDetails(int productId, String title, int numItems, String sizeSelected, String color,
			String brand) {
		super();
		this.productId = productId;
		this.title = title;
		this.numItems = numItems;
		this.sizeSelected = sizeSelected;
		this.color = color;
		this.brand = brand;
	}

	public OrderProductDetails() {
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

	public int getNumItems() {
		return numItems;
	}

	public void setNumItems(int numItems) {
		this.numItems = numItems;
	}

	public String getSizeSelected() {
		return sizeSelected;
	}

	public void setSizeSelected(String sizeSelected) {
		this.sizeSelected = sizeSelected;
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

	@Override
	public String toString() {
		return "OrderProductDetails [productId=" + productId + ", title=" + title + ", numItems=" + numItems
				+ ", sizeSelected=" + sizeSelected + ", color=" + color + ", brand=" + brand + "]";
	}

}
