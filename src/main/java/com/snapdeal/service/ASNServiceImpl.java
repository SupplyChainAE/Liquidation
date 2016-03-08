package com.snapdeal.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import com.snapdeal.dao.ASNDao;
import com.snapdeal.dto.UploadResult;
import com.snapdeal.entity.ASN;
import com.snapdeal.entity.Liquidation;
import com.snapdeal.entity.User;
import com.snapdeal.util.DateConvertor;

@Named("asnService")
public class ASNServiceImpl implements ASNService{

	@Inject
	@Named("asnDao")
	ASNDao asnDao;
	
	@Override
	public UploadResult saveFile(MultipartFile file) throws Exception
	{
		UploadResult result = new UploadResult();
		List<ASN> asnList = new ArrayList<ASN>();
		User currentUser= (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Liquidation liq = currentUser.getActiveLiquidation();
		
		String line = "", msg = "";
		String[] data;
		InputStream is = file.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		int header = 1;
		boolean error = false;
		
		while((line=reader.readLine()) != null)
		{
			data = line.split(",");
			
			if(header == 1)
			{
				if(!data[0].equals("Barcode") && !data[1].equals("Expected Date(DD-MM-YYYY)"))
				{
					error = true;
					msg = "Invalid  file";
					break;
				}
				else
				{
					header = 0;
					continue;
				}
			}
			if(!data[0].equals("") && !data[1].equals(""))
			{
				ASN asn = new ASN();
				asn.setBarcode(data[0]);
				asn.setExpectedDate(DateConvertor.convertToDate(data[1]));
				asn.setIsUsed(false);
				asn.setLiquidation(liq);
				
				asnList.add(asn);
			}
			else
			{
				error = true;
				msg = "Empty file uploaded";
				break;
			}
		}
		
		if(error)
		{
			result.setError(true);
			result.setMessage(msg);
		}
		else
		{
			result.setError(false);
		}
		
		for(ASN asn : asnList)
		{
			asnDao.saveOrUpdateASN(asn);
		}
		
		return result;
	}

}
