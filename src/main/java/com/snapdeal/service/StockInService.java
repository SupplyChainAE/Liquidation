package com.snapdeal.service;

import org.springframework.stereotype.Service;

import com.snapdeal.dto.ProductDetails;
import com.snapdeal.dto.StockInResult;

@Service
public interface StockInService {

	public StockInResult save(ProductDetails product);
	
	public StockInResult getDetails(String barcode);
}
