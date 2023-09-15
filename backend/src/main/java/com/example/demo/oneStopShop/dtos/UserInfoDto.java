package com.example.demo.oneStopShop.dtos;

public class UserInfoDto {
	
	private String token;
	
	private String role;
	
	private String firstName;
	
	private int userId;

	public UserInfoDto(String token, String role, String firstName, int userId) {
		super();
		this.token = token;
		this.role = role;
		this.firstName = firstName;
		this.userId = userId;
	}

	public UserInfoDto() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserInfoDto [token=" + token + ", role=" + role + ", firstName=" + firstName + ", userId=" + userId
				+ "]";
	}

}
