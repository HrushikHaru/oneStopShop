package com.example.demo.oneStopShop.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	
	private String firstName;
	
	private String lastName;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private int postalCode;
	
	private String phoneNumber;
	
	@ManyToOne
	@JoinColumn(name = "userId",referencedColumnName = "userId")
	private UserDetailsList userDetails;

	public UserAddress(int addressId, String firstName, String lastName, String address, String city, String state,
			int postalCode, String phoneNumber, UserDetailsList userDetails) {
		super();
		this.addressId = addressId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
		this.userDetails = userDetails;
	}

	public UserAddress() {
		super();
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public UserDetailsList getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetailsList userDetails) {
		this.userDetails = userDetails;
	}

	@Override
	public String toString() {
		return "UserAddress [addressId=" + addressId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + ", city=" + city + ", state=" + state + ", postalCode=" + postalCode
				+ ", phoneNumber=" + phoneNumber + ", userDetails=" + userDetails + "]";
	}

}
