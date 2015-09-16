package com.snapdeal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="inventory")
public class Inventory extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Column(name="barcode",length=200,unique=true,nullable=false)
	private String barcode;

	@Column(name="supc")
	private String supc;

	@Column(name="sku")
	private String sku;

	@Column(name="vendor_code",nullable=false)
	private String vendorCode;

	@Column(name="status",nullable=false)
	private String status;

	@Column(name="seller_name",nullable=false)
	private String sellerName;
	
	@Column(name="customer_name")
	private String customerName;

	@Column(name="product_name",length=2048)
	private String productName;

	@Column(name="qc_remarks")
	private String qcRemarks;
	
	@Column(name="shipping_mode")
	private String shippingMode;
	
	@Column(name="rms_center")
	private String rmsCenter;
	
	@Column(name="ticket_id")
	private String ticketId;

	@Column(name="price")
	private Long price;
	
	@Column(name="weight")
	private String weight;

	@Column(name="suborder_code")
	private String subCategory;

	@Column(name="suborder_code")
	private String suborderCode;
	
	@Column(name="order_code")
	private String orderCode;

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

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getQcRemarks() {
		return qcRemarks;
	}

	public void setQcRemarks(String qcRemarks) {
		this.qcRemarks = qcRemarks;
	}

	public String getShippingMode() {
		return shippingMode;
	}

	public void setShippingMode(String shippingMode) {
		this.shippingMode = shippingMode;
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

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public String getSuborderCode() {
		return suborderCode;
	}

	public void setSuborderCode(String suborderCode) {
		this.suborderCode = suborderCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

}
