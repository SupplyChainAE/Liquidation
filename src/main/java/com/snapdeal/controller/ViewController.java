package com.snapdeal.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snapdeal.dao.EntityDao;
import com.snapdeal.dao.InventoryDao;
import com.snapdeal.dao.LotDao;
import com.snapdeal.entity.Inventory;
import com.snapdeal.entity.Liquidation;
import com.snapdeal.entity.Lot;
import com.snapdeal.entity.User;
import com.snapdeal.service.DownloadLotService;

@Controller
@RequestMapping("/View")
public class ViewController {

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
	@Named("downloadLotService")
	DownloadLotService downloadService;
	
	public static final Logger LOGGER = Logger.getLogger(ViewController.class);
	
	@RequestMapping("/home")
	public String viewHome(ModelMap map)
	{
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Liquidation liquidation = currentUser.getActiveLiquidation();
		List<Lot> lotList = lotDao.getLotByLiquidation(liquidation);
		
		map.put("lots", lotList);
		
		return "View/view";
	}
	
	@RequestMapping("/viewLot/{id}")
	public String viewLot(@PathVariable("id") Long id,ModelMap map)
	{
		Lot lot = entityDao.findById(Lot.class, id);
		List<Inventory> inventoryList = lotDao.getInventoryOfLot(lot);
		
		map.put("lot",lot);
		map.put("inventory", inventoryList);
		
		return "View/viewInventory";
	}
	
	@RequestMapping("/inventoryDetails/{lotId}/{inventoryId}")
	public String viewInventory(@PathVariable("lotId") Long lotId,@PathVariable("inventoryId") Long inventoryId,ModelMap map)
	{
		Lot lot = entityDao.findById(Lot.class, lotId);
		List<Inventory> inventoryList = lotDao.getInventoryOfLot(lot);
		
		map.put("lot",lot);
		map.put("inventory", inventoryList);
		
		Inventory inventory = entityDao.findById(Inventory.class, inventoryId);
		map.put("inv", inventory);
		
		return "View/viewInventory";
	}
	
	@RequestMapping("/inventoryDetails")
	public @ResponseBody Inventory inventoryDetails(@RequestParam("id") Long id)
	{
		Inventory inventory = entityDao.findById(Inventory.class, id);
	
		return inventory;
	}
	
	@RequestMapping("/searchCri")
	public String  searchHome(ModelMap map)
	{
		return "View/search";
	}
	
	@RequestMapping("/search")
	public String  search(@RequestParam("barcode") String barcode,ModelMap map)
	{
		Inventory inventory = inventoryDao.getInventoryByBarcode(barcode);
		
		if(inventory == null)
		{
			map.put("error",true);
			map.put("message","No Inventory Found for Barcode");
		}
		else
		{
			map.put("inventory", inventory);
		}
		return "View/search";
	}
	
	@RequestMapping("/downloadLot/{lotId}")
	public void downloadLot(@PathVariable("lotId") Long lotId,HttpServletResponse response,ModelMap map)
	{
		Lot lot = entityDao.findById(Lot.class, lotId);
		List<Inventory> inventoryList = lotDao.getInventoryOfLot(lot);
		
		String content = downloadService.getDownloadData(inventoryList);
		try 
		{
			response.setContentType("text/csv");
			response.setHeader("Content-Disposition", "attachment; filename="+lot.getLotName()+".csv");
			response.getWriter().write(content);
			
		} catch (IOException e) {
			LOGGER.error("IO Exception in sending template",e);
		}catch (Exception e) {
			LOGGER.error("Exception in sending template",e);
		}
	}
}
