package com.snapdeal.service;

import java.util.List;

import com.snapdeal.entity.BuyerDetails;

public interface DispatchService {

	void dispatchLot(BuyerDetails buyer,List<Long> idList);
}
