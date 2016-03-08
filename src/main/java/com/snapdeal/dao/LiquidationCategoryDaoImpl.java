package com.snapdeal.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.snapdeal.entity.LiquidationCategory;

@Named("liqCategoryDao")
public class LiquidationCategoryDaoImpl implements LiquidationCategoryDao{

	@Inject
	@Named("entityDao")
	EntityDao entityDao;
	
	@Override
	@SuppressWarnings("unchecked")
	public String getLiquidationCategory(String subcategory) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select liqCat from LiquidationCategory liqCat where liqCat.subcategory = :subcategory");
		query.setParameter("subcategory",subcategory);
		
		List<LiquidationCategory> resultList = query.getResultList();
		
		if(resultList.size() != 0)
			return resultList.get(0).getLiqCategory();
		else
			return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public String getSubCategory(String liqcategory) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select liqCat from LiquidationCategory liqCat where liqCat.liqCategory = :liqcategory");
		query.setParameter("liqcategory",liqcategory);
		
		List<LiquidationCategory> resultList = query.getResultList();
		
		if(resultList.size() != 0)
			return resultList.get(0).getSubcategory();
		else
			return null;	
		}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> getAllLiquidationCategory() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select Distinct liqCat.liqCategory from LiquidationCategory liqCat");
		
		List<String> resultList = query.getResultList();
		
		if(resultList.size() != 0)
			return resultList;
		else
			return null;	
	}

}
