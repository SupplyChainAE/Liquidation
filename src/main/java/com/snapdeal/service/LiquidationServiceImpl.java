package com.snapdeal.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.snapdeal.dao.EntityDao;
import com.snapdeal.entity.Liquidation;

@Named("liquidationService")
@Transactional
public class LiquidationServiceImpl implements LiquidationService {

	@Inject
	@Named("entityDao")
	EntityDao entityDao;

	@Override
	public Long saveOrUpdateLcenter(Liquidation liquidation) {
		entityDao.saveOrUpdate(liquidation);
		return liquidation.getId();
	}

	@Override
	public void enableLcenter(Long id) {
		Liquidation persitedLcenter = entityDao.findById(Liquidation.class, id);
		persitedLcenter.setEnabled(true);
		entityDao.saveOrUpdate(persitedLcenter);
	}

	@Override
	public void disableLcenter(Long id) {
		Liquidation persitedLcenter = entityDao.findById(Liquidation.class, id);
		persitedLcenter.setEnabled(false);
		entityDao.saveOrUpdate(persitedLcenter);
	}

	@Override
	public Liquidation findLcenterByid(Long id) {
		Liquidation lCenter = entityDao.findById(Liquidation.class, id);
		return lCenter;
	}

	@Override
	public List<Liquidation> getAllLcenter() {
		List<Liquidation> lcenter = entityDao.findAll(Liquidation.class);
		return lcenter;
	}
//
//	@Override
//	public List<Warehouse> getEnabledWarehouses() {
//		return entityDao.findAllEnabledObjects(Warehouse.class);
//	}
//
	@SuppressWarnings("unchecked")
	@Override
	public boolean checkName(String lcenterName) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select liquidation.name from Liquidation liquidation where " +
		"liquidation.name = :name");
		query.setParameter("name", lcenterName);
		List<String> nameList = (List<String>)query.getResultList();
		if(nameList != null && nameList.size() > 0)
		{
			return false;	
		}
		else {
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkCode(String lcenterCode) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select liquidation.code from Liquidation liquidation where " +
		"liquidation.code = :code");
		query.setParameter("code", lcenterCode);
		List<String> nameList = (List<String>)query.getResultList();
		if(nameList != null && nameList.size() > 0)
		{
			return false;	
		}
		else {
			return true;
		}
	}

//	@Override
//	public Long getWarehouseIdByCode(String Code) {
//		EntityManager entityManager = entityDao.getEntityManager();
//		Query query = entityManager.createQuery("Select warehouse.id from Warehouse warehouse where " +
//		"warehouse.code = :code");
//		query.setParameter("code", Code);
//		Long id = (Long) query.getSingleResult();
//		return id;
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Warehouse> getEnabledSDPlusWarehouses() {
//		EntityManager entityManager = entityDao.getEntityManager();
//		Query query = entityManager.createQuery("Select warehouse from Warehouse warehouse where " +
//		"warehouse.warehouseType = 'SD Plus'");
//		List<Warehouse> whList = query.getResultList();
//		return whList;
//	}
//	
	
}
