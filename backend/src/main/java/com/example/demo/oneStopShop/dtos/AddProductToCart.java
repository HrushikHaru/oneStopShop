package com.example.demo.oneStopShop.dtos;

public class AddProductToCart {
	
	private int productId;
	
	private String sizeSelected;
	
	private int numItems;
	
	private int userId;

	public AddProductToCart(int productId, String sizeSelected, int numItems, int userId) {
		super();
		this.productId = productId;
		this.sizeSelected = sizeSelected;
		this.numItems = numItems;
		this.userId = userId;
	}

	public AddProductToCart() {
		super();
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getSizeSelected() {
		return sizeSelected;
	}

	public void setSizeSelected(String sizeSelected) {
		this.sizeSelected = sizeSelected;
	}

	public int getNumItems() {
		return numItems;
	}

	public void setNumItems(int numItems) {
		this.numItems = numItems;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AddProductToCart [productId=" + productId + ", sizeSelected=" + sizeSelected + ", numItems=" + numItems
				+ ", userId=" + userId + "]";
	}

}
