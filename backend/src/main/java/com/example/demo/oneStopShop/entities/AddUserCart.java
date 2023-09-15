package com.example.demo.oneStopShop.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AddUserCart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	
	private int productId;
	
	private String sizeSelected;
	
	private int numItems;
	
	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private UserDetailsList userDetailsList;

	public AddUserCart(int cartId, int productId, String sizeSelected, int numItems, UserDetailsList userDetailsList) {
		super();
		this.cartId = cartId;
		this.productId = productId;
		this.sizeSelected = sizeSelected;
		this.numItems = numItems;
		this.userDetailsList = userDetailsList;
	}

	public AddUserCart() {
		super();
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
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

	public UserDetailsList getUserDetailsList() {
		return userDetailsList;
	}

	public void setUserDetailsList(UserDetailsList userDetailsList) {
		this.userDetailsList = userDetailsList;
	}

	@Override
	public String toString() {
		return "AddUserCart [cartId=" + cartId + ", productId=" + productId + ", sizeSelected=" + sizeSelected
				+ ", numItems=" + numItems + ", userDetailsList=" + userDetailsList + "]";
	}

}
