package com.example.demo.oneStopShop.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class WishList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wishListId;
	
	private int productId;
	
	@ManyToOne
	@JoinColumn(name = "userId",referencedColumnName = "userId")
	private UserDetailsList userDetailsList;

	public WishList(int wishListId, int productId, UserDetailsList userDetailsList) {
		super();
		this.wishListId = wishListId;
		this.productId = productId;
		this.userDetailsList = userDetailsList;
	}

	public WishList() {
		super();
	}

	public int getWishListId() {
		return wishListId;
	}

	public void setWishListId(int wishListId) {
		this.wishListId = wishListId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public UserDetailsList getUserDetailsList() {
		return userDetailsList;
	}

	public void setUserDetailsList(UserDetailsList userDetailsList) {
		this.userDetailsList = userDetailsList;
	}

	@Override
	public String toString() {
		return "WishList [wishListId=" + wishListId + ", productId=" + productId + ", userDetailsList="
				+ userDetailsList + "]";
	}

}
