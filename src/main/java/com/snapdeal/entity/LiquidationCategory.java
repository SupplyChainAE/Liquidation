package com.snapdeal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category_mapping")
public class LiquidationCategory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="liquidation_category",nullable=false)
	private String liqCategory;
	
	@Column(name="subcategory",nullable=false,unique=true)
	private String subcategory;

	public void setLiqCategory(String liqCategory) {
		this.liqCategory = liqCategory;
	}

	public String getLiqCategory() {
		return liqCategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
