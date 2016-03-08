package com.snapdeal.dao;

import java.util.List;

import com.snapdeal.entity.ASN;

public interface ASNDao {

	public void saveOrUpdateASN(ASN asn);
	public List<ASN> findByDate(String startDate,String endDate);
	public ASN findByCode(String barcode);
}
