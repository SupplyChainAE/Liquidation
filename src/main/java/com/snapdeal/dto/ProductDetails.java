package com.snapdeal.dto;

public class ProductDetails {

	private String barcode;
	private String supc;
	private String sku;
	private String orderCode;
	private String suborderCode;
	private String subCategory;
	private String issueCategory;
	private String ticketId;
	private String customerName;
	private String vendorCode;
	private String sellerName;
	private String productName;
	private long price;
	private String weight;
	private String rmsCenter;
	private String fulfillmentModel;
	private String liqCategory;
	private String remarks;
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getSupc() {
		return supc;
	}
	public void setSupc(String supc) {
		this.supc = supc;
	}
	public String getSuborderCode() {
		return suborderCode;
	}
	public void setSuborderCode(String suborderCode) {
		this.suborderCode = suborderCode;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getIssueCategory() {
		return issueCategory;
	}
	public void setIssueCategory(String issueCategory) {
		this.issueCategory = issueCategory;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getRmsCenter() {
		return rmsCenter;
	}
	public void setRmsCenter(String rmsCenter) {
		this.rmsCenter = rmsCenter;
	}
	public String getTicketId() {
		return ticketId;
	}
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public void setFulfillmentModel(String fulfillmentModel) {
		this.fulfillmentModel = fulfillmentModel;
	}
	public String getFulfillmentModel() {
		return fulfillmentModel;
	}
	public void setLiqCategory(String liqCategory) {
		this.liqCategory = liqCategory;
	}
	public String getLiqCategory() {
		return liqCategory;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRemarks() {
		return remarks;
	}
}
