package com.snapdeal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.snapdeal.entity.Liquidation;

@Service
public interface LiquidationService {

	public Long saveOrUpdateLcenter(Liquidation liquidation);
	public void enableLcenter(Long id);
	public void disableLcenter(Long id);
	public Liquidation findLcenterByid(Long id);
	public List<Liquidation> getAllLcenter();
//	public List<Warehouse> getEnabledWarehouses();
//	public List<Warehouse> getEnabledSDPlusWarehouses();
	public boolean checkName(String lcenterName);
	public boolean checkCode(String lcenterCode);
//	public Long getWarehouseIdByCode(String Code);
	
	
//	public void saveOrupdateSkuType(SkuType skuType);
//	public List<SkuType> getAllSkuType();
//	public SkuType findSkuTypeById(Long id);
//	public void removeSkuType(Long id);
	
}
