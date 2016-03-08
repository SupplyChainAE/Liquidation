package com.snapdeal.service;

import java.util.List;

import com.snapdeal.entity.Inventory;

public interface DownloadLotService {

	String getDownloadData(List<Inventory> inventoryList);
}
