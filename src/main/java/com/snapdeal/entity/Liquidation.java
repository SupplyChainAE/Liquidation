package com.snapdeal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="liquidation")
public class Liquidation extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="name")
	private String name;

	@Column(name="code")
	private String code;
	
	@Column(name="lot_size")
	private Long lotSize;

	@Column(name="enabled")
	private boolean enabled;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setLotSize(Long lotSize) {
		this.lotSize = lotSize;
	}

	public Long getLotSize() {
		return lotSize;
	}
	
}
