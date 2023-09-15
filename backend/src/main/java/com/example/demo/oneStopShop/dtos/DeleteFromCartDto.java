package com.example.demo.oneStopShop.dtos;

public class DeleteFromCartDto {
	
	private int userId;
	
	private int productId;
	
	private int numItems;
	
	private String sizeSelected;
	
	private int cartId;
	
	private String dateTime;

	public DeleteFromCartDto(int userId, int productId, int numItems, String sizeSelected, int cartId, String dateTime) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.numItems = numItems;
		this.sizeSelected = sizeSelected;
		this.cartId = cartId;
		this.dateTime = dateTime;
	}

	public DeleteFromCartDto() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "DeleteFromCartDto [userId=" + userId + ", productId=" + productId + ", numItems=" + numItems
				+ ", sizeSelected=" + sizeSelected + ", cartId=" + cartId + "]";
	}

}
