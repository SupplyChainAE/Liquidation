package com.snapdeal.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.snapdeal.dao.ASNDao;
import com.snapdeal.dto.UploadResult;
import com.snapdeal.entity.ASN;
import com.snapdeal.service.ASNService;

@Controller
@RequestMapping("/ASN")
public class ASNController {

	@Inject
	@Named("asnService")
	ASNService asnService;
	
	@Inject
	@Named("asnDao")
	ASNDao asnDao;
	
	public static final Logger LOGGER = Logger.getLogger(ASNController.class);
	
	@RequestMapping("/home")
	public String AsnHome(ModelMap map)
	{
		return "ASN/asn";
	}
	
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file,ModelMap map)
	{
		try
		{
			UploadResult result = asnService.saveFile(file);
			
			if(result.getError()){
				map.put("error", result.getError());
				map.put("message", "Successfully uploaded");
			}
			else
			{
				map.put("error", result.getError());
				map.put("message", result.getMessage());
			}
		}
		catch(IOException ex)
		{
			LOGGER.error("IO Error", ex);
		}
		catch(Exception ex)
		{
			LOGGER.error("Exception", ex);
		}
		return "ASN/asn";
	}
	
	@RequestMapping("/template")
	public void downloadTemplate(HttpServletResponse response)
	{
		try 
		{
			String line="Barcode,Expected Date(DD-MM-YYYY)\n";
			response.setContentType("text/csv");
			response.setHeader("Content-Disposition", "attachment; filename=ASNTemplate.csv");
			response.setContentLength(line.length());
			response.getWriter().write(line);
			
		} catch (IOException e) {
			LOGGER.error("IO Exception in sending template",e);
		}catch (Exception e) {
			LOGGER.error("Exception in sending template",e);
		}
	}
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String searchHome(ModelMap map)
	{
		return "ASN/search";
	}
	
	@RequestMapping(value = "/search",method=RequestMethod.POST)
	public String search(@RequestParam("daterange") String daterange ,ModelMap map)
	{
		String[] date = daterange.split(":");
		List<ASN> asnList = asnDao.findByDate(date[0], date[1]);
		
		if(asnList.size() > 0 )
			map.put("data",asnList);
		else
			map.put("msg","No ASN found");
		
		return "ASN/search";
	}
	
	@RequestMapping(value = "/searchByCode",method=RequestMethod.GET)
	public String searchCodeHome(ModelMap map)
	{
		return "ASN/searchBycri";
	}
	
	@RequestMapping(value = "/searchByCode",method=RequestMethod.POST)
	public String searchCode(@RequestParam("barcode") String barcode ,ModelMap map)
	{
		ASN asn = asnDao.findByCode(barcode);
		
		if(asn !=null )
			map.put("asn",asn);
		else
			map.put("msg","No ASN found");
		
		return "ASN/searchBycri";
	}
}
