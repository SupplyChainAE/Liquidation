package com.snapdeal.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.snapdeal.component.Constants;
import com.snapdeal.dao.EntityDao;
import com.snapdeal.entity.Counter;

@Transactional
@Named("counterService")
public class CounterServiceImpl implements CounterService{

	@Inject
	@Named("entityDao")
	EntityDao entityDao;
	
	@Override
	public void createCounter(Long liquidationId) {

		Counter counter1 = new Counter();
		counter1.setLotType(Constants.LOT_TYPE_LOT);
		counter1.setLotUsed(0L);
		counter1.setLiq(liquidationId);
		entityDao.save(counter1);
		
		Counter counter2= new Counter();
		counter2.setLotType(Constants.LOT_TYPE_RCD);
		counter2.setLotUsed(0L);
		counter2.setLiq(liquidationId);
		entityDao.save(counter2);
		
		
	}
	
	

}
