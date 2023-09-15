package com.example.demo.oneStopShop.dtos;

public class UserAddressDtoSend {
	
	private int addressId;
	
	private String firstName;
	
	private String lastName;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private int postalCode;
	
	private String phoneNumber;

	public UserAddressDtoSend(String firstName, String lastName, String address, String city, String state, int postalCode,
			String phoneNumber, int addressId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
		this.addressId = addressId;
	}

	public UserAddressDtoSend() {
		super();
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

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	@Override
	public String toString() {
		return "UserAddressDtoSend [addressId=" + addressId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + ", city=" + city + ", state=" + state + ", postalCode=" + postalCode
				+ ", phoneNumber=" + phoneNumber + "]";
	}

}
