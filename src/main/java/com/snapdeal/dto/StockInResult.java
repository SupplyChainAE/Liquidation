package com.snapdeal.dto;

import com.snapdeal.entity.Inventory;


public class StockInResult {

	private Inventory inventory;
	
	private ProductDetails productDetails;
	
	private String errorMessage;
	
	private boolean error = false;

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}

	public ProductDetails getProductDetails() {
		return productDetails;
	}
	
}
