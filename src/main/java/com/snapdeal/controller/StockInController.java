package com.snapdeal.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.snapdeal.component.SessionDetails;
import com.snapdeal.dao.EntityDao;
import com.snapdeal.dao.LotDao;
import com.snapdeal.dto.ProductDetails;
import com.snapdeal.dto.StockInResult;
import com.snapdeal.entity.Liquidation;
import com.snapdeal.entity.Lot;
import com.snapdeal.service.StockInService;

@Controller
@RequestMapping("/StockIn")
@SessionAttributes({"activeLot","product"})
public class StockInController {
	
	
	@Inject 
	@Named("stockinService")
	StockInService stockInService;
	
	@Inject
	@Named("sessionDetails")
	SessionDetails sessionDetails;

	@Inject
	@Named("entityDao")
	EntityDao entityDao;

	@Inject
	@Named("lotDao")
	LotDao lotDao;
	
	public static final Logger LOGGER = Logger.getLogger(StockInController.class);
	
	@ModelAttribute("activeLot")
    public Lot getActiveLot() 
	{System.out.println("Executed");
		Liquidation liq = entityDao.findById(Liquidation.class,sessionDetails.getActiveLcenter());
		Lot activeLot = lotDao.getActiveLotByLiquidation(liq);	 
		return activeLot;
    }
	
	
	@RequestMapping("/home")
	public String stockinHome(/*@ModelAttribute("activeLot") Lot activeLot,*/
			@RequestParam(value="error",required=false) Boolean error,
			@RequestParam(value="message",required=false) String message,SessionStatus session,ModelMap map)
	{	
//		session.setComplete();
		Liquidation liq = entityDao.findById(Liquidation.class,sessionDetails.getActiveLcenter());
		Lot activeLot = lotDao.getActiveLotByLiquidation(liq);	
		map.put("lot",activeLot);
		
		if(message !=null)
		{
			map.put("message", message);
			map.put("error", error);
		}
		return "Stockin/home";
	}
	
	@RequestMapping("/uploadHome")
	public String uploadHome(ModelMap map)
	{
		return "Stockin/upload";
	}
	
	@RequestMapping("/check")
	public String check(@ModelAttribute("lot")Lot activeLot,@RequestParam("barcode") String barcode,
			ModelMap map)
	{	
		barcode = getBarcode(barcode);
		StockInResult result = stockInService.getDetails(barcode);
		
		if(result.isError())
		{
			map.put("error", true);
			map.put("message",result.getErrorMessage());	
		}
		else
		{
			map.put("product", result.getProductDetails());
			map.put("error", false);
		}
		map.put("lot",activeLot);
		return "Stockin/home";
	}
	

	@RequestMapping("/save")
	public String save(@ModelAttribute("activeLot")Lot activeLot,@ModelAttribute("product") ProductDetails  product, 
			@RequestParam(value="remarks",required=false) String remarks,SessionStatus session,ModelMap map)
	{
		product.setRemarks(remarks);
		StockInResult result = stockInService.save(product);

		if(result.isError())
		{
			map.put("error", true);
			map.put("message",result.getErrorMessage());	
		}
		else
		{
//			map.put("inventory", result.getInventory());
			map.put("error", false);
			map.put("message","Successfuly Stocked In");
		}
		session.setComplete();
		return "redirect:/StockIn/home";
	}
	
	@RequestMapping("/saveUpload")
	public String saveUpload(@RequestParam("file") MultipartFile  file, ModelMap map)
	{
		return "Stockin/upload";
	}
	
	@RequestMapping("/template")
	public void downloadTemplate(HttpServletResponse response)
	{
		try 
		{
			String line="Barcode\n";
			response.setContentType("text/csv");
			response.setHeader("Content-Disposition", "attachment; filename=PutawayTemplate.csv");
			response.setContentLength(line.length());
			response.getWriter().write(line);
			
		} catch (IOException e) {
			LOGGER.error("IO Exception in sending template",e);
		}catch (Exception e) {
			LOGGER.error("Exception in sending template",e);
		}
	}

	private String getBarcode(String barcode) 
	{
		if(barcode.substring(0,4).equals("RET-") || barcode.substring(0,4).equals("ret-"))
			barcode = barcode.substring(4);
		return barcode;
	}
}
