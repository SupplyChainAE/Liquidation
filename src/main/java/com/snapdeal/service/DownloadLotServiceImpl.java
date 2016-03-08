package com.snapdeal.service;

import java.util.List;

import javax.inject.Named;

import org.springframework.stereotype.Service;

import com.snapdeal.entity.Inventory;

@Service
@Named("downloadLotService")
public class DownloadLotServiceImpl implements DownloadLotService{

	@Override
	public String getDownloadData(List<Inventory> inventoryList) 
	{
		StringBuilder sb = new StringBuilder();
		sb.append(getHeader());
		
		for(Inventory inv : inventoryList)
		{
			sb.append(joinString(inv.getBarcode()))
			.append(joinString(inv.getCustomerName()))
			.append(joinString(inv.getIssueCategory()))
			.append(joinString(inv.getOrderCode()))
			.append(joinString(inv.getPrice().toString()))
			.append(joinString(inv.getProductName().replace(",", ";")))
			.append(joinString(inv.getFulfillmentModel()))
			.append(joinString(inv.getRmsCenter()))
			.append(joinString(inv.getSellerName()))
			.append(joinString(inv.getVendorCode()))
			.append(joinString(inv.getSku()))
			.append(joinString(inv.getSupc()))
			.append(joinString(inv.getStatus()))
			.append(joinString(inv.getSubCategory()))
			.append(joinString(inv.getLiqCategory()))
			.append(joinString(inv.getSuborderCode()))
			.append(joinString(inv.getTicketId()))
			.append(joinString(inv.getWeight()))
			.append(joinString(inv.getLot().getLotName()))
			.append(joinString(inv.getRemarks()))
			.append(joinString(inv.getCreated().toString()))
			.append(joinString(inv.getUpdated().toString()))
			.append("\n");
		}
		
		return sb.toString();
	}

	
	private String getHeader() {
		String header = "BarCode,Customer Name,Issue Category," +
						"Order Code,Price,Product Name,Fulfilment Model," +
						"RMS Center,Seller Name,Seller Code,SKU,SUPC,Status," +
						"Sub Category,Liquidation Category,SubOrder Code," +
						"Ticket ID,Weight,Lot Number,Remarks,Created,Updated\n";
		return header;
	}
	
	private String joinString(String str) {
		return str + ",";
	}

}
