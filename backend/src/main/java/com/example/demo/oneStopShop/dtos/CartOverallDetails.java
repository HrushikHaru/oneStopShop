package com.example.demo.oneStopShop.dtos;

public class CartOverallDetails {
	
	private int itemsCost;
	
	private int discountedPrice;
	
	private int totalCost;

	public CartOverallDetails(int itemsCost, int discountedPrice,
			int totalCost) {
		super();
		this.itemsCost = itemsCost;
		this.discountedPrice = discountedPrice;
		this.totalCost = totalCost;
	}

	public CartOverallDetails() {
		super();
	}

	public int getItemsCost() {
		return itemsCost;
	}

	public void setItemsCost(int itemsCost) {
		this.itemsCost = itemsCost;
	}

	public int getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(int discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	@Override
	public String toString() {
		return "CartOverallDetails [itemsCost=" + itemsCost + ", discountedPrice=" + discountedPrice + ", totalCost="
				+ totalCost + "]";
	}

}
