package com.example.demo.oneStopShop.responses;

import java.util.Date;

public class UserCreatedResponse {
	
	private String message;
	
	private Date createdOn;

	public UserCreatedResponse(String message, Date createdOn) {
		super();
		this.message = message;
		this.createdOn = createdOn;
	}

	public UserCreatedResponse() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "UserCreatedResponse [message=" + message + ", createdOn=" + createdOn + "]";
	}

}
