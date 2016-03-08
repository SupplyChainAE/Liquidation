package com.snapdeal.dao;

import java.util.List;

import com.snapdeal.entity.Inventory;
import com.snapdeal.entity.Liquidation;
import com.snapdeal.entity.Lot;

public interface LotDao {
	
	public List<Inventory> getInventoryOfLot(Lot lot);
	
	public List<Inventory> getOpenInventoryOfLot(Lot lot);

	public Lot getLotByLotNumber(Long lotNumber,Liquidation liquidation);
	
	public Lot assignLotToInventory(Liquidation liquidation);
	
	public Long createNewLot(Liquidation liquidation,String lotType);
	
	public Integer updateLot(Lot lot,Long quantity);
	
	public void closeLot(Lot lot);
	
	public List<Lot> getLotByLiquidation(Liquidation liquidation);
	
	public Lot getActiveLotByLiquidation(Liquidation liquidation);

	Lot getLotByLotName(String lotName, Liquidation liquidation);
	
}
