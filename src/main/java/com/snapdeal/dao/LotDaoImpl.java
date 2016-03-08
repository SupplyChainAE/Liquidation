package com.snapdeal.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.snapdeal.component.Constants;
import com.snapdeal.entity.Counter;
import com.snapdeal.entity.Inventory;
import com.snapdeal.entity.Liquidation;
import com.snapdeal.entity.Lot;

@Transactional
@Named("lotDao")
public class LotDaoImpl implements LotDao
{
	@Inject
	@Named("entityDao")
	EntityDao entityDao;
	
	@Inject
	@Named("inventoryDao")
	InventoryDao inventoryDao;

	@Override
	@SuppressWarnings("unchecked")
	public List<Inventory> getInventoryOfLot(Lot lot) 
	{
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select inv from Inventory inv where inv.lot = :lot");
		query.setParameter("lot",lot);
		
		List<Inventory> resultList = query.getResultList();
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Inventory> getOpenInventoryOfLot(Lot lot)
	{
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select inv from Inventory inv where inv.lot = :lot and inv.status NOT IN (:status)");
		query.setParameter("lot",lot);
		
		List<String> statusList = new ArrayList<String>();
		statusList.add(Constants.INVENTORY_LIQUIDATED_STATUS);
		statusList.add(Constants.LOT_LIQUIDATED_STATUS);
		query.setParameter("status",statusList);
		
		List<Inventory> resultList = query.getResultList();
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Lot assignLotToInventory(Liquidation liquidation) 
	{
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("SELECT lot from Lot lot where lot.liq = :liq and lot.status = :status");
		query.setParameter("liq", liquidation.getId());
		query.setParameter("status", Constants.LOT_CREATION_STATUS);
		
		List<Lot> resultList = query.getResultList();
		Lot lot;
//		Long lotMaxSize,lotCurrentSize,newLotNumber;

		/*** Auto Lot creation ****/
//		if(resultList != null && resultList.size()>0)
//		{
//			lot = resultList.get(0);
//			lotMaxSize = lot.getMaxSize();
//			lotCurrentSize = lot.getCurrentSize();
//			
//			if(lotMaxSize - lotCurrentSize > 1)
//			{
//				updateLot(lot,1L);
//				return lot;
//			}
//			else
//			{
//				updateLot(lot,1L);
//				closeLot(lot);
//				return entityDao.findById(Lot.class, lot.getId());
//			}
//		}
//		else
//		{
//			newLotNumber = createNewLot(liquidation);
//			lot = getLotByLotNumber(newLotNumber,liquidation);
//			updateLot(lot,1L);
//			return lot;
//		
//		}
	/**********     ***********/
	
	/************ Manual Lot creation ************/
	if(resultList != null)
	{
		lot = resultList.get(0);
		updateLot(lot,1L);
		return lot;
	}
	else
		return null;
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public Long createNewLot(Liquidation liquidation,String lotType) 
	{
		EntityManager entityManager = entityDao.getEntityManager();
		Query query1 = entityManager.createQuery("SELECT counter from Counter counter where counter.liq = :liq AND lot_type = :type");
		query1.setParameter("liq", liquidation.getId());
		query1.setParameter("type", lotType);
		
		List<Counter> resultList = query1.getResultList();
		Long lotNumber = resultList.get(0).getLotUsed() + 1;
		
		Query query2 = entityManager.createQuery("UPDATE Counter counter SET counter.lotUsed = counter.lotUsed+1 where counter.liq = :liq AND lot_type = :type");
		query2.setParameter("liq", liquidation.getId());
		query2.setParameter("type", lotType);
		query2.executeUpdate();
		
		Lot lot = new Lot();
		
		lot.setLotNumber(lotNumber);
		lot.setCurrentSize(0L);
		lot.setLotName(liquidation.getCode()+"-"+lotType+"-"+lotNumber);
		lot.setLiq(liquidation.getId());
		lot.setType(lotType);
//		lot.setMaxSize(liquidation.getLotSize());
		lot.setStatus(Constants.LOT_CREATION_STATUS);
		
		entityDao.saveOrUpdate(lot);
		
		return lot.getId();
	}

	@Override
	public Integer updateLot(Lot lot, Long quantity) 
	{
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("UPDATE Lot lot SET lot.currentSize = lot.currentSize + :quantity where lot.id = :id");
		query.setParameter("quantity", quantity);
		query.setParameter("id", lot.getId());
		Integer result = query.executeUpdate();
		
		return result;
	}

	@Override
	public void closeLot(Lot lot) 
	{
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("UPDATE Lot lot SET lot.status = :status  where lot.id = :id");
		query.setParameter("status", Constants.LOT_CLOSED_STATUS);
		query.setParameter("id", lot.getId());
		query.executeUpdate();
		
		
		List<Inventory> invList = getOpenInventoryOfLot(lot);
		List<Long> idList = new ArrayList<Long>();
		
		for(Inventory inventory : invList)
		{
			idList.add(inventory.getId());
		}
		inventoryDao.updateInventoryOnLotClose(idList);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Lot getLotByLotNumber(Long lotNumber,Liquidation liquidation) 
	{
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select lot from Lot lot where lot.lotNumber = :lotNumber and lot.liq = :liq");
		query.setParameter("lotNumber",lotNumber);
		query.setParameter("liq",liquidation.getId());
		
		List<Lot> resultList = query.getResultList();
		if(resultList.size()>0)
			return resultList.get(0);
		else
			return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Lot> getLotByLiquidation(Liquidation liquidation) 
	{
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select lot from Lot lot where lot.liq = :liq");
		query.setParameter("liq",liquidation.getId());

		List<Lot> resultList = query.getResultList();

		if(resultList.size()>0)
			return resultList;
		else
			return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Lot getActiveLotByLiquidation(Liquidation liquidation) 
	{
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("SELECT lot from Lot lot where lot.liq = :liq and lot.status = :status");
		query.setParameter("liq", liquidation.getId());
		query.setParameter("status", Constants.LOT_CREATION_STATUS);
		
		List<Lot> resultList = query.getResultList();
		Lot lot;
	
		if(resultList != null && resultList.size()>0)
		{
			lot = resultList.get(0);
			return lot;
		}
		else
			return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Lot getLotByLotName(String lotName,Liquidation liquidation) 
	{
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select lot from Lot lot where lot.lotName = :lotName and lot.liq = :liq");
		query.setParameter("lotName",lotName);
		query.setParameter("liq",liquidation.getId());
		
		List<Lot> resultList = query.getResultList();
		if(resultList.size()>0)
			return resultList.get(0);
		else
			return null;
	}
}
