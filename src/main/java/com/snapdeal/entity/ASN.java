package com.snapdeal.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.security.core.context.SecurityContextHolder;

@Entity
@Table(name="advance_notification")
public class ASN extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name="barcode",unique=true)
	private String barcode;
	
	@ManyToOne
	private Liquidation liquidation;
	
	@Column(name="used")
	private Boolean isUsed = false;

	@Column(name="expected_date")
	private Date expectedDate;
	
	@Column(name="receive_date")
	private Date receiveDate;
	
	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Liquidation getLiquidation() {
		return liquidation;
	}

	public void setLiquidation(Liquidation liquidation) {
		this.liquidation = liquidation;
	}

	public Boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}
	
	@Override
	@PrePersist
	protected void onCreate()
	{
		User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.setCreated(new Date());
		this.setUpdated(new Date());
		this.setUpdatedBy(sessionUser);
		this.setCreatedBy(sessionUser);
		this.liquidation = sessionUser.getActiveLiquidation();
	}

	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}

	public Date getExpectedDate() {
		return expectedDate;
	}
}
