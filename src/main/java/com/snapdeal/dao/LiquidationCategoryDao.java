package com.snapdeal.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.snapdeal.entity.Inventory;

@Repository
public interface LiquidationCategoryDao {

	public String getLiquidationCategory(String subcategory);

	public String getSubCategory(String liqcategory);
	
	public List<String> getAllLiquidationCategory();
	
}
