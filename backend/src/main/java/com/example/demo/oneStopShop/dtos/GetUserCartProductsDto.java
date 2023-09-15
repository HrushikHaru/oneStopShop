package com.example.demo.oneStopShop.dtos;

public class GetUserCartProductsDto {
	
	private GetAllProductsDto productDetails;
	
	private int numItems;
	
	private String sizeSelected;
	
	private int cartId;

	public GetUserCartProductsDto(GetAllProductsDto productDetails, int numItems, String sizeSelected, int cartId) {
		super();
		this.productDetails = productDetails;
		this.numItems = numItems;
		this.sizeSelected = sizeSelected;
		this.cartId = cartId;
	}

	public GetUserCartProductsDto() {
		super();
	}

	public GetAllProductsDto getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(GetAllProductsDto productDetails) {
		this.productDetails = productDetails;
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

	@Override
	public String toString() {
		return "GetUserCartProductsDto [productDetails=" + productDetails + ", numItems=" + numItems + ", sizeSelected="
				+ sizeSelected + ", cartId=" + cartId + "]";
	}

}
