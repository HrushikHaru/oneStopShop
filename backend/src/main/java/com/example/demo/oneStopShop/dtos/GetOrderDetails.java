package com.example.demo.oneStopShop.dtos;

import java.util.List;

public class GetOrderDetails {
	
	private String dateTime;
	
	private List<OrderProductDetails> productDetails;

	public GetOrderDetails(String dateTime, List<OrderProductDetails> productDetails) {
		super();
		this.dateTime = dateTime;
		this.productDetails = productDetails;
	}

	public GetOrderDetails() {
		super();
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public List<OrderProductDetails> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(List<OrderProductDetails> productDetails) {
		this.productDetails = productDetails;
	}

	@Override
	public String toString() {
		return "GetOrderDetails [dateTime=" + dateTime + ", productDetails=" + productDetails + "]";
	}

}
