package com.snapdeal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.snapdeal.component.Constants;
import com.snapdeal.dao.EntityDao;
import com.snapdeal.dao.InventoryDao;
import com.snapdeal.dao.LiquidationCategoryDao;
import com.snapdeal.dao.LotDao;
import com.snapdeal.dto.DispatchInventory;
import com.snapdeal.entity.BuyerDetails;
import com.snapdeal.entity.Inventory;
import com.snapdeal.entity.Liquidation;
import com.snapdeal.entity.Lot;
import com.snapdeal.entity.User;

@Controller
@RequestMapping("/Dispatch")
public class DispatchController {
	
	@Inject
	@Named("lotDao")
	LotDao lotDao;
	
	@Inject
	@Named("entityDao")
	EntityDao entityDao;
	

	@Inject
	@Named("inventoryDao")
	InventoryDao inventoryDao;
	
	@Inject
	@Named("liqCategoryDao")
	LiquidationCategoryDao liqCatDao;
	
	@RequestMapping("/home")
	public String home(ModelMap map)
	{
		return "Dispatch/home";
	}

	@RequestMapping("/criHome")
	public String criHome(ModelMap map)
	{
		return "Dispatch/homeCri";
	}
	
	@RequestMapping("/getLot")
	public String getLotDetails(@RequestParam("lotName")String lotNumber ,ModelMap map)
	{
//		lotNumber = lotNumber.replaceAll("[^0-9]", "");
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Liquidation liquidation = currentUser.getActiveLiquidation();
		Lot lot = lotDao.getLotByLotName(lotNumber, liquidation);
		
		if(lot == null)
		{
			map.put("error",true);
			map.put("message", "No Lot Found");
		}
		else if(! lot.getStatus().equals(Constants.LOT_CLOSED_STATUS))
		{
			map.put("error",true);
			map.put("message","Lot can not be dispatched ");
		}
		else
		{
			List<Inventory> inventoryList = lotDao.getOpenInventoryOfLot(lot);
			List<DispatchInventory> dispatchList = new ArrayList<DispatchInventory>();
			
			if(inventoryList != null && inventoryList.size() > 0)
			{
				for(Inventory inv : inventoryList)
				{
					DispatchInventory di = new DispatchInventory();
					di.setInventory(inv);
					di.setCheck(true);
					
					dispatchList.add(di);
				}
				map.put("show", true);
				map.put("lotId", lot.getId());
				map.put("dispatchList", dispatchList);
				map.put("liqCat", liqCatDao.getAllLiquidationCategory());
			}
			else
			{
				map.put("error",true);
				map.put("message","No Inventory Found for Lot");
			}
		}
		return "Dispatch/home";
	}
	
	@RequestMapping("/buyerDetails")
	public String getBuyerDetails(@RequestParam(value="check",required=false)List<Long> check,
			@RequestParam("lotId") Long lotId,ModelMap map)
	{
		if(check != null && check.size()>0)
		{
			List<Inventory> inventoryList = inventoryDao.getInventoryListfromId(check);
			map.put("dispatchList",inventoryList);
			return "Dispatch/dispatch";
		}
		else
		{
			Lot lot = entityDao.findById(Lot.class, lotId);
			List<Inventory> inventoryList = lotDao.getOpenInventoryOfLot(lot);
			List<DispatchInventory> dispatchList = new ArrayList<DispatchInventory>();
			
			if(inventoryList != null)
			{
				for(Inventory inv : inventoryList)
				{
					DispatchInventory di = new DispatchInventory();
					di.setInventory(inv);
					di.setCheck(true);
					
					dispatchList.add(di);
				}
				map.put("show", true);
				map.put("dispatchList", dispatchList);
				map.put("liqCat", liqCatDao.getAllLiquidationCategory());
				map.put("error",true);
				map.put("message","Select Inventory to Dispatch");
			}
			
			return "Dispatch/home";	
		}
	}
	
	@RequestMapping("/dispatchLot")
	public String dispatchLot(@RequestParam("invId")List<Long> idList ,
			@ModelAttribute("buyerForm") BuyerDetails buyer, ModelMap map)
	{
		if(buyer != null)
		{
			entityDao.saveOrUpdate(buyer);
			inventoryDao.groupDispatchInventory(idList, buyer);
			
			/**check for lot open inventory **/
			Inventory inv = entityDao.findById(Inventory.class, idList.get(0));
			Lot lot = inv.getLot();
			lot.setDispatchPrice(lot.getDispatchPrice()+Long.parseLong(buyer.getPrice()));
			List<Inventory> openInventory = lotDao.getOpenInventoryOfLot(lot);
			if(openInventory.size() == 0)
			{
				lot.setStatus(Constants.LOT_LIQUIDATED_STATUS);
				
			}
			entityDao.saveOrUpdate(lot);
		}
		
		map.put("error", "false");
		map.put("message", "Successfully Dispatched");
		return "Dispatch/home";
	}
	
	@RequestMapping("/check")
	public String check(@RequestParam("barcode")String barcode ,ModelMap map)
	{
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Liquidation liquidation = currentUser.getActiveLiquidation();
		
		
		if( ! inventoryDao.checkInventoryByLiquidation(barcode, liquidation))
		{
			map.put("error",true);
			map.put("message", "No Inventory Found");
		}
		else
		{
			Inventory inventory = inventoryDao.getInventoryByBarcode(barcode);
			
			if(inventory != null)
			{
				map.put("inventory", inventory);
			}
			else
			{
				map.put("error",true);
				map.put("message","No Inventory Found");
			}
		}
		return "Dispatch/homeCri";
	}
	
	@RequestMapping("/buyerDetailsCri")
	public String getBuyerDetailsCri(@RequestParam("invId")Long invId ,ModelMap map)
	{
		Inventory inventory = entityDao.findById(Inventory.class, invId);
		
		if(inventory.getStatus().equals(Constants.INVENTORY_LIQUIDATED_STATUS) 
				|| inventory.getStatus().equals(Constants.LOT_LIQUIDATED_STATUS) )
		{
			map.put("error",true);
			map.put("message","Inventory already dispatched");
			return "Dispatch/homeCri";
		}
		else
		{
			map.put("inventory",inventory);
			return "Dispatch/dispatchCri";	
		}
	}

	@RequestMapping("/dispatch")
	public String dispatchCri(@RequestParam("invId")Long id ,
			@ModelAttribute("buyerForm") BuyerDetails buyer, ModelMap map)
	{
		if(buyer != null)
		{
			entityDao.saveOrUpdate(buyer);
//			System.out.println(id);
			Inventory persistedInventory = entityDao.findById(Inventory.class, id);
			persistedInventory.setBuyer(buyer);
			persistedInventory.setStatus(Constants.INVENTORY_LIQUIDATED_STATUS);
			entityDao.saveOrUpdate(persistedInventory);		
		}
		
		map.put("error", "false");
		map.put("message", "Successfully Dispatched");
		return "Dispatch/homeCri";
	}
	
	@RequestMapping("/filterCategory")
	public String filterCategory(@RequestParam("lotId")Long lotId ,
			@RequestParam("liqCat") List<String> catList, ModelMap map)
	{
		List<Inventory> inventoryList = inventoryDao.getInventoryFromLotofLiquidationCategory(lotId, catList);
		List<DispatchInventory> dispatchList = new ArrayList<DispatchInventory>();
		
		if(inventoryList != null){
			for(Inventory inv : inventoryList)
			{
					DispatchInventory di = new DispatchInventory();
					di.setInventory(inv);
					di.setCheck(true);
					
					dispatchList.add(di);
			}
		}
		map.put("lotId", lotId);
		map.put("dispatchList", dispatchList);
		map.put("liqCat", liqCatDao.getAllLiquidationCategory());
		map.put("show", true);
		return "Dispatch/home";
	}
}
