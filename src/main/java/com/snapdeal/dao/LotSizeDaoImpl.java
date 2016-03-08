//package com.snapdeal.dao;
//
//import java.util.List;
//
//import javax.inject.Inject;
//import javax.inject.Named;
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//
//import org.springframework.transaction.annotation.Transactional;
//
//import com.snapdeal.entity.LotSize;
//
//@Transactional
//@Named("lotSizeDao")
//public class LotSizeDaoImpl implements LotSizeDao{
//	
//	@Inject
//	@Named("entityDao")
//	EntityDao entityDao;
//
//	@Override
//	public void saveOrUpdateLot(LotSize lotSize) {
//		entityDao.saveOrUpdate(lotSize);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public LotSize getLotSizeByLcenter(Long id) {
//		EntityManager entityManager = entityDao.getEntityManager();
//		Query query = entityManager.createQuery("Select lotSize from LotSize lotSize where lotsize.lCenter = :lcenter");
//				query.setParameter("lcenter",id);
//				List<LotSize> lotSizeList = (List<LotSize>)query.getResultList();
//				if(lotSizeList != null && lotSizeList.size() > 0)
//				{
//					return lotSizeList.get(0);	
//				}
//				else {
//					return null;
//				}
//	}
//	
//	
//	
//	@Override
//	public LotSize findLotSizeById(Long id) {
//		LotSize lotSize = entityDao.findById(LotSize.class, id);
//	
//		return lotSize;
//	}
//
//
//}
