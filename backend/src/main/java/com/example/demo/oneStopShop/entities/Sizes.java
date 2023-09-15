package com.example.demo.oneStopShop.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Sizes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sizeId;
	
	private int sizeS;
	
	private int sizeM;
	
	private int sizeL;
	
	@OneToOne(mappedBy = "sizes")
	private Products products;

	public Sizes(int sizeId, int sizeS, int sizeM, int sizeL, Products products) {
		super();
		this.sizeId = sizeId;
		this.sizeS = sizeS;
		this.sizeM = sizeM;
		this.sizeL = sizeL;
		this.products = products;
	}

	public Sizes() {
		super();
	}

	public int getSizeId() {
		return sizeId;
	}

	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}

	public int getSizeS() {
		return sizeS;
	}

	public void setSizeS(int sizeS) {
		this.sizeS = sizeS;
	}

	public int getSizeM() {
		return sizeM;
	}

	public void setSizeM(int sizeM) {
		this.sizeM = sizeM;
	}

	public int getSizeL() {
		return sizeL;
	}

	public void setSizeL(int sizeL) {
		this.sizeL = sizeL;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Sizes [sizeId=" + sizeId + ", sizeS=" + sizeS + ", sizeM=" + sizeM + ", sizeL=" + sizeL + ", products="
				+ products + "]";
	}

}
