package com.snapdeal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.snapdeal.dto.ProductDetails;
import com.snapdeal.util.Util;

public class Dao {
	private DataSource dataSourceLocal;
	
	private DataSource dataSourceRms;
	
	private DataSource dataSourceDwh;

	public DataSource getDataSourceRms() {
		return dataSourceRms;
	}

	public void setDataSourceRms(DataSource dataSourceRms) {
		this.dataSourceRms = dataSourceRms;
	}

	public DataSource getDataSourceLocal() {
		return dataSourceLocal;
	}

	public void setDataSourceLocal(DataSource dataSourceLocal) {
		this.dataSourceLocal = dataSourceLocal;
	}
	
	public static final Logger LOGGER = Logger.getLogger(Dao.class);
	
	public ProductDetails getDetails(String barcode)
	{
		Connection connection;
		Statement statement;
		ProductDetails productDetails = null;
		try{
			connection = (Connection) dataSourceRms.getConnection();
			statement = (Statement) connection.createStatement();
			String query = "select cri.vendor_code as vendor,cri.seller_name as seller,cri.product_name as productName, " +
			"cri.item_price as price, cri.sku_code as sku,cri.order_code,cri.fulfillment_model_code as fulfillment_model, " +
			"cri.suborder_code as suborder,cri.supc as supc,cri.code as barcode,cri.weight as weight," + 
			"cri.returns_center_code as rms_center," +
			"cc.issue_category_code as issuecategory, cc.zendesk_ticket_code as ticket_id, " + 
			"pd.name as customer_name " +
			"from customer_returned_item cri JOIN customer_complaint cc On cri.cc_id = cc.id " + 
			"JOIN customer_returned_package crp on crp.id = cri.crp_id " +
			"JOIN pickup_detail pd ON pd.id = crp.pickup_detail_id " + 
			"where cri.code = '"+barcode+"'";
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet != null)
			{
				productDetails = new ProductDetails();
				if(resultSet.next()){
					productDetails.setBarcode(Util.specialTrim(resultSet.getString("barcode")));
					productDetails.setVendorCode(Util.specialTrim(resultSet.getString("vendor")));
					productDetails.setSellerName(Util.specialTrim(resultSet.getString("seller")));
					productDetails.setProductName(Util.specialTrim(resultSet.getString("productName")));
					productDetails.setPrice(resultSet.getLong("price"));
					productDetails.setSku(Util.specialTrim(resultSet.getString("sku")));
					productDetails.setOrderCode(Util.specialTrim(resultSet.getString("order_code")));
					productDetails.setFulfillmentModel(Util.specialTrim(resultSet.getString("fulfillment_model")));
					productDetails.setSuborderCode(Util.specialTrim(resultSet.getString("suborder")));
					
					productDetails.setSupc(Util.specialTrim(resultSet.getString("supc")));
					productDetails.setWeight(Util.specialTrim(resultSet.getString("weight")));
					productDetails.setRmsCenter(Util.specialTrim(resultSet.getString("rms_center")));
					
					productDetails.setIssueCategory(Util.specialTrim(resultSet.getString("issuecategory")));
					productDetails.setTicketId(Util.specialTrim(resultSet.getString("ticket_id")));
					productDetails.setCustomerName(Util.specialTrim(resultSet.getString("customer_name")));
				}
			}
			resultSet.close();
			connection.close();
			
			connection = (Connection) dataSourceDwh.getConnection();
			statement = (Statement) connection.createStatement();
			query = "select subcategory_url as subcategory from dwh.d_product where supc= '"+productDetails.getSupc()+"'";
			resultSet = statement.executeQuery(query);
			if(resultSet != null) {
				if(resultSet.next()) {
					productDetails.setSubCategory(Util.specialTrim(resultSet.getString("subcategory")));
				}
			}
			resultSet.close();
			connection.close();
			
				
		return productDetails;
	}
	catch(SQLException e)
	{
		LOGGER.error("SQL Exception", e);
	}
	catch(Exception e)
	{
		LOGGER.error("Exception", e);
	}
	return null;
}

	public void setDataSourceDwh(DataSource dataSourceDwh) {
		this.dataSourceDwh = dataSourceDwh;
	}

	public DataSource getDataSourceDwh() {
		return dataSourceDwh;
	}
}