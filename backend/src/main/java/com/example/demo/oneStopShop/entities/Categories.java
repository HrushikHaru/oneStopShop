package com.example.demo.oneStopShop.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Categories {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	
	private String categoryName;
	
	@OneToMany(mappedBy = "categories")
	private Set<SubCategories> subCategories;

	public Categories(int categoryId, String categoryName, Set<SubCategories> subCategories) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.subCategories = subCategories;
	}

	public Categories() {
		super();
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<SubCategories> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(Set<SubCategories> subCategories) {
		this.subCategories = subCategories;
	}

	@Override
	public String toString() {
		return "Categories [categoryId=" + categoryId + ", categoryName=" + categoryName + ", subCategories="
				+ subCategories + "]";
	}
}
