package com.snapdeal.controller;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snapdeal.component.Constants;
import com.snapdeal.component.SessionDetails;
import com.snapdeal.dao.LotDao;
import com.snapdeal.entity.Liquidation;
import com.snapdeal.entity.User;
import com.snapdeal.service.CounterService;
import com.snapdeal.service.LiquidationService;
import com.snapdeal.service.UserAuthenticationService;
import com.snapdeal.service.UserService;

@Controller
@RequestMapping("/Liquidation")
public class LiquidationController {

	@Inject
	@Named("liquidationService")
	LiquidationService liquidationService;

	@Inject
	@Named("counterService")
	CounterService counterService;
	
	@Inject
	@Named("lotDao")
	LotDao lotDao;
	
	@Inject
	@Named("sessionDetails")
	SessionDetails sessionDetails;

	@Inject
	@Named("userService")
	UserService userService;

	@Autowired
	UserAuthenticationService userAuthenticationService;

	@RequestMapping("/create")
	public String createLcenter(ModelMap map)
	{
		Liquidation liquidation = new Liquidation();
		map.put("lcenter", liquidation);	
		return "Lcenter/create";
	}

	@RequestMapping("/view")
	public String viewAllLcenter(ModelMap map)
	{
		List<Liquidation> lcenter = liquidationService.getAllLcenter();
		map.put("lcenter",lcenter);

		return "Lcenter/view";
	}


	@RequestMapping(value="/change/{id}",method=RequestMethod.GET)
	public String changeLcenter(@PathVariable("id") Long id,ModelMap map)
	{
		User sessionUser = sessionDetails.getSessionUser();
		Liquidation activeLcenter = liquidationService.findLcenterByid(id);
		sessionUser.setActiveLiquidation(activeLcenter);
		userService.saveOrUpdateUser(sessionUser);
		userAuthenticationService.loadUserByUsername(sessionUser.getUsername());
		map.put("message", "Liquidation center Changed Successfully.");
		return "redirect:/home";
	}


	@RequestMapping("/save")
	public String saveLcenter(@ModelAttribute("Lcenter") Liquidation liquidation, ModelMap map)
	{	
		if(liquidation.getId() != null)
		{
			Liquidation persistedLcenter = liquidationService.findLcenterByid(liquidation.getId());
			persistedLcenter.setName(liquidation.getName());
			persistedLcenter.setCode(liquidation.getCode());
			persistedLcenter.setLotSize(liquidation.getLotSize());
			liquidationService.saveOrUpdateLcenter(persistedLcenter);
		}
		else {
			liquidation.setEnabled(true);
			liquidation.setId(liquidationService.saveOrUpdateLcenter(liquidation));
			counterService.createCounter(liquidation.getId());
			// Manual Lot Creation
			lotDao.createNewLot(liquidation,Constants.LOT_TYPE_LOT);
			
		}
		map.put("message", "Liquidation saved successfully");
		List<Liquidation> lcenter = liquidationService.getAllLcenter();
		map.put("lcenter", lcenter);
		return "Lcenter/view";
	}

	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String editLcenter(@PathVariable("id") Long id,ModelMap map)
	{
		Liquidation lcenter = liquidationService.findLcenterByid(id);
		map.put("lcenter",lcenter );
		map.put("edit", true);
		return "Lcenter/create";
	}

	@RequestMapping(value="/disable/{id}",method=RequestMethod.GET)
	public String disableLcenter(@PathVariable("id") Long id,ModelMap map)
	{
		liquidationService.disableLcenter(id);
		List<Liquidation> liquidation = liquidationService.getAllLcenter();
		map.put("lcenter", liquidation);
		map.put("message", "Liquidation center disabled successfully");
		return "Lcenter/view";
	}

	@RequestMapping(value="/enable/{id}",method=RequestMethod.GET)
	public String enableLcenter(@PathVariable("id") Long id,ModelMap map)
	{
		liquidationService.enableLcenter(id);
		List<Liquidation> liquidation = liquidationService.getAllLcenter();
		map.put("lcenter", liquidation);
		map.put("message", "Liquidation Center enabled successfully");
		return "Lcenter/view";
	}

	@RequestMapping("/checkName")
	public @ResponseBody String checkName(@ModelAttribute("name") String lcenterName)
	{
		boolean result = liquidationService.checkName(lcenterName);
		if(result)
		{
			return "success";
		}
		else{
			return "failure";
		}
	}

	@RequestMapping("/checkCode")
	public @ResponseBody String checkCode(@ModelAttribute("code") String lcenterCode)
	{
		boolean result = liquidationService.checkCode(lcenterCode);
		if(result)
		{
			return "success";
		}
		else{
			return "failure";
		}
	}
	//	
}