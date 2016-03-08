package com.snapdeal.dto;

import com.snapdeal.entity.Inventory;

public class DispatchInventory {

	private Inventory inventory;
	
	private boolean check;

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public boolean isCheck() {
		return check;
	}
}
