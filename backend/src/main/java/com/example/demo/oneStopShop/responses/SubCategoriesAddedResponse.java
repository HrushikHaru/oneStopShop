package com.example.demo.oneStopShop.responses;

import java.util.Date;

public class SubCategoriesAddedResponse {
	
	private String message;
	
	private Date addedOn;

	public SubCategoriesAddedResponse(String message, Date addedOn) {
		super();
		this.message = message;
		this.addedOn = addedOn;
	}

	public SubCategoriesAddedResponse() {
		super();
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
		return "SubCategoriesAddedResponse [message=" + message + ", addedOn=" + addedOn + "]";
	}

}
