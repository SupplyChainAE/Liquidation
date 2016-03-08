package com.snapdeal.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.snapdeal.component.Constants;
import com.snapdeal.entity.BuyerDetails;
import com.snapdeal.entity.Inventory;
import com.snapdeal.entity.Liquidation;

@Transactional
@Named("inventoryDao")
public class InventoryDaoImpl implements InventoryDao{

	@Inject
	@Named("entityDao")
	EntityDao entityDao;

	
	@Override
	@SuppressWarnings("unchecked")
	public boolean checkInventory(String barcode) 
	{
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("SELECT inv from Inventory inv where inv.barcode = :barcode");
		query.setParameter("barcode", barcode);
		
		List<Inventory> resultList = query.getResultList();
		
		if(resultList != null && resultList.size()>0)
			return true;
		else
			return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean checkInventoryByLiquidation(String barcode , Liquidation liquidation) 
	{
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("SELECT inv from Inventory inv where inv.barcode = :barcode " +
				"and inv.liquidation = :liquidation");
		query.setParameter("barcode", barcode);
		query.setParameter("liquidation", liquidation);
		
		List<Inventory> resultList = query.getResultList();
		
		if(resultList != null && resultList.size()>0)
			return true;
		else
			return false;
	}
	@Override
	public Long saveOrUpdateInventory(Inventory inventory) 
	{
		entityDao.saveOrUpdate(inventory);
		return inventory.getId();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Inventory> getInventoryListfromId(List<Long> idList) 
	{
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("SELECT inv from Inventory inv where inv.id IN (:idList)");
		List<Inventory> resultList = new ArrayList<Inventory>();
		List<Long> batchId = new ArrayList<Long>();
		
		int batchSize = 100,count =0 ,curr = 0;
		while(idList.size() != count)
		{
			curr++;
			batchId.add(idList.get(count));
			count++;
		
			if(curr == batchSize || (count == idList.size() && batchId.size() > 0))
			{
				query.setParameter("idList", batchId);
				resultList.addAll(query.getResultList());
				curr = 0;
				entityManager.flush();
				entityManager.clear();
				batchId.clear();
			}
		}
		
		return resultList;
	}

	@Override
	public void groupDispatchInventory(List<Long> idList ,BuyerDetails buyer) 
	{
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("UPDATE Inventory inv SET inv.status = :status ,inv.buyer = :buyer" +
				"  where inv.id IN (:idList)");
		query.setParameter("status", Constants.LOT_LIQUIDATED_STATUS);
		query.setParameter("buyer", buyer);
			
		List<Long> batchId = new ArrayList<Long>();
		int batchSize = 100,count =0 ,curr = 0;
	
		while(idList.size() != count)
		{
			curr++;
			batchId.add(idList.get(count));
			count++;
			
			if(curr == batchSize || (count == idList.size() && batchId.size() > 0))
			{
				query.setParameter("idList", batchId);
				query.executeUpdate();
				curr = 0;
				entityManager.flush();
				entityManager.clear();
				batchId.clear();
			}
		}
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public Inventory getInventoryByBarcode(String barcode) 
	{
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("SELECT inv from Inventory inv where inv.barcode = :barcode");
		query.setParameter("barcode", barcode);
		
		List<Inventory> resultList = query.getResultList();
		
		if(resultList != null && resultList.size()>0)
			return resultList.get(0);
		else
			return null;
	}

	@Override
	public void updateInventoryOnLotClose(List<Long> idList) 
	{
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("UPDATE Inventory inv SET inv.status = :status where inv.id IN (:idList)");
		query.setParameter("status", Constants.LOT_CLOSED_STATUS);
			
		List<Long> batchId = new ArrayList<Long>();
		int batchSize = 100,count =0 ,curr = 0;
	
		while(idList.size() != count)
		{
			curr++;
			batchId.add(idList.get(count));
			count++;
			
			if(curr == batchSize || (count == idList.size() && batchId.size() > 0))
			{
				query.setParameter("idList", batchId);
				query.executeUpdate();
				curr = 0;
				entityManager.flush();
				entityManager.clear();
				batchId.clear();
			}
		}
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Inventory> getInventoryFromLotofLiquidationCategory(Long lotId,
			List<String> liqcategory) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select inv from Inventory inv where inv.lot.id = :lotId and inv.liqCategory IN (:liqCategory)");
		query.setParameter("lotId", lotId);
		query.setParameter("liqCategory", liqcategory);

		List<Inventory> resultList = query.getResultList();

		if (resultList != null && resultList.size() > 0)
			return resultList;
		else
			return null;
	}
	
}
