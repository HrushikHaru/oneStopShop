package com.example.demo.oneStopShop.dtos;

public class WishListDto {
	
	private int productId;
	
	private int userId;

	public WishListDto(int productId, int userId) {
		super();
		this.productId = productId;
		this.userId = userId;
	}

	public WishListDto() {
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

	@Override
	public String toString() {
		return "WishListDto [productId=" + productId + ", userId=" + userId + "]";
	}

}
