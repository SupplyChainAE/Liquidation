//package com.snapdeal.component;
//
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.stereotype.Component;
//
//import com.snapdeal.dto.ASNMapper;
//import com.snapdeal.entity.ASN;
//import com.snapdeal.util.DateConvertor;
//
//@Component
//public class ASNItemProcessor implements ItemProcessor<ASNMapper, ASN>{
//
//	@Override
//	public ASN process(ASNMapper asnMapper) throws Exception 
//	{
//		ASN asn = new ASN();
//		asn.setBarcode(asnMapper.getBarcode());
//		asn.setExpectedDate(DateConvertor.convertToDate(asnMapper.getExpectedDate()));
//		
//		return asn;
//	}
//
//}
