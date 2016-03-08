package com.snapdeal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="lot")
public class Lot extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="lot_number")
	private Long lotNumber;
	
	@Column(name="liq_center")
	private Long liq;
	
	@Column(name="status")
	private String status;
	
//	@Column(name="max_size")
//	private Long maxSize;
	
	@Column(name="current_size")
	private Long currentSize;
	
	@Column(name="lot_name")
	private String lotName;
	
	@Column(name="dispatch_price")
	private Long dispatchPrice = 0L;
	
	@Column(name="type")
	private String type;
	
	public Long getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(Long lotNumber) {
		this.lotNumber = lotNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

//	public Long getMaxSize() {
//		return maxSize;
//	}
//
//	public void setMaxSize(Long maxSize) {
//		this.maxSize = maxSize;
//	}

	public Long getCurrentSize() {
		return currentSize;
	}

	public void setCurrentSize(Long currentSize) {
		this.currentSize = currentSize;
	}

	
	public void setLiq(Long liq) {
		this.liq = liq;
	}

	public Long getLiq() {
		return liq;
	}

	public void setLotName(String lotName) {
		this.lotName = lotName;
	}

	public String getLotName() {
		return lotName;
	}

	public void setDispatchPrice(Long dispatchPrice) {
		this.dispatchPrice = dispatchPrice;
	}

	public Long getDispatchPrice() {
		return dispatchPrice;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
