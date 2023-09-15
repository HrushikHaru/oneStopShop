package com.example.demo.oneStopShop.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class SubCategories {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int subCategoryId;
	
	private String subCategoryName;
	
	@ManyToOne
	@JoinColumn(name = "categoryId",referencedColumnName = "categoryId")
	private Categories categories;
	
	@OneToMany(mappedBy = "subCategory")
	private Set<Products> products;

	public SubCategories(int subCategoryId, String subCategoryName, Categories categories) {
		super();
		this.subCategoryId = subCategoryId;
		this.subCategoryName = subCategoryName;
		this.categories = categories;
	}

	public SubCategories() {
		super();
	}

	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "SubCategories [subCategoryId=" + subCategoryId + ", subCategoryName=" + subCategoryName
				+ ", categories=" + categories + "]";
	}

}
