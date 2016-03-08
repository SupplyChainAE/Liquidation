package com.snapdeal.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.core.context.SecurityContextHolder;

import com.snapdeal.dao.Dao;
import com.snapdeal.dao.InventoryDao;
import com.snapdeal.dao.LiquidationCategoryDao;
import com.snapdeal.dao.LotDao;
import com.snapdeal.dto.ProductDetails;
import com.snapdeal.dto.StockInResult;
import com.snapdeal.entity.Inventory;
import com.snapdeal.entity.Liquidation;
import com.snapdeal.entity.Lot;
import com.snapdeal.entity.User;


@Named("stockinService")
public class StockInServiceImpl implements StockInService{

	@Inject
	@Named("inventoryDao")
	InventoryDao inventoryDao;
	
	@Inject
	@Named("lotDao")
	LotDao lotDao;
	
	@Inject
	@Named("liqCategoryDao")
	LiquidationCategoryDao liqCategoryDao;
	
	@Inject
	@Named("Dao")
	Dao dao;
	
//	@Override
//	public StockInResult save(String barcode) 
//	{
//		StockInResult result = new StockInResult();
//		ProductDetails productDetails;
//		Inventory inventory;
//		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		Liquidation liquidation = loggedUser.getActiveLiquidation();
//		Lot lot;
//		
//		if(! inventoryDao.checkInventory(barcode))
//		{
//			productDetails = dao.getDetails(barcode);
//			
//			if ( productDetails != null )
//			{
//				inventory = new Inventory();
//				inventory.setBarcode(productDetails.getBarcode());
//				inventory.setCustomerName(productDetails.getCustomerName());
//				inventory.setIssueCategory(productDetails.getIssueCategory());
//				inventory.setOrderCode(productDetails.getOrderCode());
//				inventory.setProductName(productDetails.getProductName());
//				inventory.setRmsCenter(productDetails.getRmsCenter());
//				inventory.setSellerName(productDetails.getSellerName());
//				inventory.setSku(productDetails.getSku());
//				inventory.setSubCategory(productDetails.getSubCategory());
//				inventory.setSuborderCode(productDetails.getSuborderCode());
//				inventory.setSupc(productDetails.getSupc());
//				inventory.setTicketId(productDetails.getTicketId());
//				inventory.setVendorCode(productDetails.getVendorCode());
//				inventory.setWeight(productDetails.getWeight());
//				inventory.setLiquidation(liquidation);
//				inventory.setPrice(productDetails.getPrice());
//				inventory.setFulfillmentModel(productDetails.getFulfillmentModel());
//				
//				String liqcat = liqCategoryDao.getLiquidationCategory(productDetails.getSubCategory());
//				inventory.setLiqCategory(liqcat);
//				
//				lot = lotDao.assignLotToInventory(liquidation);
//				
//				if(lot != null)
//					inventory.setLot(lot);
//				else
//				{
//					result.setError(true);
//					result.setErrorMessage("Unable to Assign Lot");
//					return result;
//				}
//				
//				inventory.setStatus(lot.getStatus());
//				
//				inventoryDao.saveOrUpdateInventory(inventory);
//				result.setInventory(inventory);
//			
//			}
//			else
//			{
//				result.setError(true);
//				result.setErrorMessage("Invalid Code");
//			}
//		}
//		else
//		{
//			result.setError(true);
//			result.setErrorMessage("Already exists in Inventory");
//		}
//		
//		return result;
//	}

	@Override
	public StockInResult save(ProductDetails productDetails) 
	{
		StockInResult result = new StockInResult();
		Inventory inventory;
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Liquidation liquidation = loggedUser.getActiveLiquidation();
		Lot lot;
		
		inventory = new Inventory();
		inventory.setBarcode(productDetails.getBarcode());
		inventory.setCustomerName(productDetails.getCustomerName());
		inventory.setIssueCategory(productDetails.getIssueCategory());
		inventory.setOrderCode(productDetails.getOrderCode());
		inventory.setProductName(productDetails.getProductName());
		inventory.setRmsCenter(productDetails.getRmsCenter());
		inventory.setSellerName(productDetails.getSellerName());
		inventory.setSku(productDetails.getSku());
		inventory.setSubCategory(productDetails.getSubCategory());
		inventory.setSuborderCode(productDetails.getSuborderCode());
		inventory.setSupc(productDetails.getSupc());
		inventory.setTicketId(productDetails.getTicketId());
		inventory.setVendorCode(productDetails.getVendorCode());
		inventory.setWeight(productDetails.getWeight());
		inventory.setLiquidation(liquidation);
		inventory.setPrice(productDetails.getPrice());
		inventory.setFulfillmentModel(productDetails.getFulfillmentModel());
		inventory.setLiqCategory(productDetails.getLiqCategory());
		inventory.setRemarks(productDetails.getRemarks());
		
		lot = lotDao.assignLotToInventory(liquidation);
		
		if(lot != null)
			inventory.setLot(lot);
		else
		{
			result.setError(true);
			result.setErrorMessage("Unable to Assign Lot");
			return result;
		}
		
		inventory.setStatus(lot.getStatus());
		
		inventoryDao.saveOrUpdateInventory(inventory);
		result.setInventory(inventory);
		
		return result;
	}

	@Override
	public StockInResult getDetails(String barcode) 
	{
		StockInResult result = new StockInResult();
		ProductDetails productDetails;
		
		if(! inventoryDao.checkInventory(barcode))
		{
			productDetails = dao.getDetails(barcode);
//			&& productDetails.getBarcode() != null
			if ( productDetails != null )
			{
				String liqcat = liqCategoryDao.getLiquidationCategory(productDetails.getSubCategory());
				productDetails.setLiqCategory(liqcat);
				result.setProductDetails(productDetails);
				result.setError(false);
			}
			else
			{
				result.setError(false);
				result.setErrorMessage("Invalid Code");
			}
		}
		else
		{
			result.setError(true);
			result.setErrorMessage("Already exists in Inventory");
		}
		
		return result;
	}

}
