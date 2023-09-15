package com.example.demo.oneStopShop.responses;

import java.util.Date;

public class ProductImgAddedResponse {
	
	private String imgName;
	
	private Date addedOn;

	public ProductImgAddedResponse(String imgName, Date addedOn) {
		super();
		this.imgName = imgName;
		this.addedOn = addedOn;
	}

	public ProductImgAddedResponse() {
		super();
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	@Override
	public String toString() {
		return "ProductImgAddedResponse [imgName=" + imgName + ", addedOn=" + addedOn + "]";
	}

}
