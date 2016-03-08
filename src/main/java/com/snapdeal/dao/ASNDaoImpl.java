package com.snapdeal.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import com.snapdeal.entity.ASN;
import com.snapdeal.entity.Liquidation;
import com.snapdeal.entity.User;
import com.snapdeal.util.DateConvertor;

@Named("asnDao")
@Transactional
public class ASNDaoImpl implements ASNDao{

	@Inject
	@Named("entityDao")
	EntityDao entityDao;
	
	@Override
	public void saveOrUpdateASN(ASN asn) {
		
		entityDao.saveOrUpdate(asn);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ASN> findByDate(String startDate, String endDate) 
	{
		User currentUser= (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Liquidation liq = currentUser.getActiveLiquidation();
		
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("SELECT asn from ASN asn where asn.expectedDate " +
				"BETWEEN :start AND :end AND asn.liquidation = :liq");
		query.setParameter("start", DateConvertor.convertToDate(startDate));
		query.setParameter("end", DateConvertor.convertToDate(endDate));
		query.setParameter("liq",liq );
		List<ASN> resultList = query.getResultList();
		
		if(resultList.size()>0)
			return resultList;
		else
			return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public ASN findByCode(String barcode) 
	{
		User currentUser= (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Liquidation liq = currentUser.getActiveLiquidation();
		
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("SELECT asn from ASN asn where asn.barcode = :code and asn.liquidation =:liq");
		query.setParameter("code", barcode);
		query.setParameter("liq",liq );
		
		List<ASN> resultList = query.getResultList();
		
		if(resultList.size()>0)
			return resultList.get(0);
		else
			return null;
	}
}
