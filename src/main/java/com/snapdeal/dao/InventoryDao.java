package com.snapdeal.dao;

import java.util.List;

import com.snapdeal.entity.BuyerDetails;
import com.snapdeal.entity.Inventory;
import com.snapdeal.entity.Liquidation;

public interface InventoryDao {

	public boolean checkInventory(String barcode);
	
	public boolean checkInventoryByLiquidation(String barcode , Liquidation liquidation);
	
	public Inventory getInventoryByBarcode(String barcode);
	
	public Long saveOrUpdateInventory(Inventory inventory);
	
	public void groupDispatchInventory(List<Long> idList , BuyerDetails buyer);
	
	public List<Inventory> getInventoryListfromId(List<Long> idList);
	
	public void updateInventoryOnLotClose(List<Long> idList);

	public List<Inventory> getInventoryFromLotofLiquidationCategory(Long lotId ,List<String> liqcategory);
	
}
