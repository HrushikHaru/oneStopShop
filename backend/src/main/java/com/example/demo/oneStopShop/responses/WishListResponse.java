package com.example.demo.oneStopShop.responses;

import java.util.Date;

public class WishListResponse {
	
	private String resposne;
	
	private Date updatedOn;

	public WishListResponse(String resposne, Date updatedOn) {
		super();
		this.resposne = resposne;
		this.updatedOn = updatedOn;
	}

	public WishListResponse() {
		super();
	}

	public String getResposne() {
		return resposne;
	}

	public void setResposne(String resposne) {
		this.resposne = resposne;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "WishListResponse [resposne=" + resposne + ", updatedOn=" + updatedOn + "]";
	}

}
