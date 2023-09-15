package com.example.demo.oneStopShop.responses;

import java.util.Date;

public class CategoryAddedResponse {
	
	private String message;
	
	private Date addedOn;
	
	public CategoryAddedResponse(String message, Date addedOn) {
		this.message = message;
		this.addedOn = addedOn;
	}
	
	public CategoryAddedResponse() {
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	@Override
	public String toString() {
		return "CategoryAddedResponse [message=" + message + ", addedOn=" + addedOn + "]";
	}

}
