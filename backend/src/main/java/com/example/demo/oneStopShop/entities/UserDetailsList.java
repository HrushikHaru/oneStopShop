package com.example.demo.oneStopShop.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class UserDetailsList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private String role;
	
	@OneToMany(mappedBy = "userDetailsList")
	private Set<AddUserCart> addCart;
	
	@OneToMany(mappedBy = "userDetailsList")
	private Set<WishList> wishList;
	
	@OneToMany(mappedBy = "userDetails")
	private Set<UserAddress> userAddress;
	
	@OneToMany(mappedBy = "userDetails")
	private Set<OrderSummaryDetails> orderSummary;

	public UserDetailsList(int userId, String firstName, String lastName, String email, String password, String role) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public UserDetailsList() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", password=" + password + ", role=" + role + "]";
	}
	
}
