package com.snapdeal.controller;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.snapdeal.component.Constants;
import com.snapdeal.component.SessionDetails;
import com.snapdeal.dao.EntityDao;
import com.snapdeal.dao.LotDao;
import com.snapdeal.entity.Liquidation;
import com.snapdeal.entity.Lot;

@Controller
@RequestMapping("/LotCreate")
public class LotController {

	@Inject
	@Named("entityDao")
	EntityDao entityDao;
	

	@Inject
	@Named("lotDao")
	LotDao lotDao;
	
	@Inject
	@Named("sessionDetails")
	SessionDetails sessionDetails;

//	@RequestMapping("/lotSize")
//	public String lotSize(ModelMap map) {
//		System.out.println("lcenter:" + sessionDetails.getActiveLcenter());
//		LotSize lotSize = lotsizeDao.getLotSizeByLcenter(sessionDetails.getActiveLcenter());
//		
//		map.put("lotSize", lotSize);
//		return "Lot/lotSize";
//	}
	
	
//	@RequestMapping("/save")
//	public String saveUser(@ModelAttribute("lotSize") LotSize lotSize,ModelMap map)
//	{
//		System.out.println("size:" + lotSize.getSize());
//		 
//		
//		if(lotSize.getId() == null)
//		{
//			lotSize.setlCenter(sessionDetails.getActiveLcenter());
//			lotsizeDao.saveOrUpdateLot(lotSize);
//			
//		}
//		else
//		{
//			LotSize persistedLotSize = lotsizeDao.findLotSizeById(lotSize.getId());
//			
//			persistedLotSize.setlCenter(sessionDetails.getActiveLcenter());
//			persistedLotSize.setSize(lotSize.getSize());
//			lotsizeDao.saveOrUpdateLot(persistedLotSize);
//		 }
//		return "Lot/lotSize";
//	}
	
	@RequestMapping("/home")
	public String lotHome(ModelMap map)
	{	
		Liquidation liq = entityDao.findById(Liquidation.class,sessionDetails.getActiveLcenter());
		Lot lot = lotDao.getActiveLotByLiquidation(liq);
		map.put("lot",lot);
		return "Lot/lotCreate";
	}
	
	@RequestMapping("/create")
	public String createLot(@RequestParam("id") Long id,@RequestParam("type") String type,ModelMap map)
	{	
		Liquidation liq = entityDao.findById(Liquidation.class,sessionDetails.getActiveLcenter());
		Lot oldLot = entityDao.findById(Lot.class, id);
		lotDao.closeLot(oldLot);
		
		String lotType;
		if(type.equals(Constants.LOT_TYPE_LOT))
			lotType = Constants.LOT_TYPE_LOT;
		else
			lotType = Constants.LOT_TYPE_RCD;

		Long newLotId = lotDao.createNewLot(liq,lotType);
	
		Lot lot = entityDao.findById(Lot.class, newLotId);
		map.put("lot",lot);
		return "Lot/lotCreate";
	}
	
}
