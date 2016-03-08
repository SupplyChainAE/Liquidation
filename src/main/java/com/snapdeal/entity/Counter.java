package com.snapdeal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="counter")
public class Counter extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="liq_centre")
	private Long liq;
	
	@Column(name="lot_used")
	private Long lotUsed;
	
	@Column(name="lot_type")
	private String lotType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLotUsed() {
		return lotUsed;
	}

	public void setLotUsed(Long lotUsed) {
		this.lotUsed = lotUsed;
	}

	public void setLiq(Long liq) {
		this.liq = liq;
	}

	public Long getLiq() {
		return liq;
	}

	public void setLotType(String lotType) {
		this.lotType = lotType;
	}

	public String getLotType() {
		return lotType;
	}
	
	
	
}
