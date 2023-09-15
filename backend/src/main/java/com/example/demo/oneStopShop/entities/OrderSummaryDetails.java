package com.example.demo.oneStopShop.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderSummaryDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderDetailsId;
	
	private String productId;
	
	private String sizeSelected;
	
	private String numItems;
	
	private String dateTime;
	
	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private UserDetailsList userDetails;

	public OrderSummaryDetails(int orderDetailsId, String productId, String sizeSelected, String numItems,
			UserDetailsList userDetails, String dateTime) {
		super();
		this.orderDetailsId = orderDetailsId;
		this.productId = productId;
		this.sizeSelected = sizeSelected;
		this.numItems = numItems;
		this.userDetails = userDetails;
		this.dateTime = dateTime;
	}

	public OrderSummaryDetails() {
		super();
	}

	public int getOrderDetailsId() {
		return orderDetailsId;
	}

	public void setOrderDetailsId(int orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getSizeSelected() {
		return sizeSelected;
	}

	public void setSizeSelected(String sizeSelected) {
		this.sizeSelected = sizeSelected;
	}

	public String getNumItems() {
		return numItems;
	}

	public void setNumItems(String numItems) {
		this.numItems = numItems;
	}

	public UserDetailsList getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetailsList userDetails) {
		this.userDetails = userDetails;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "OrderSummaryDetails [orderDetailsId=" + orderDetailsId + ", productId=" + productId + ", sizeSelected="
				+ sizeSelected + ", numItems=" + numItems + ", userDetails=" + userDetails + "]";
	}

}
