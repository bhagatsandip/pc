package com.planetclubs.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.planetclubs.model.ClubsPoolBookingMap;
import com.planetclubs.model.PoolBookingUsages;
import com.planetclubs.model.Usage;

@Repository("poolBookingUsagesDAO")
public class PoolBookingUsagesDAOImpl implements PoolBookingUsagesDAO{

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional
	public int getPoolBookingUsagesCountById(int clubPoolBookingId) {
		
		List<PoolBookingUsages> lstPoolBookingUsages = sessionFactory.getCurrentSession().createCriteria(PoolBookingUsages.class).add(Restrictions.eq("clubPoolBookingId", clubPoolBookingId)).list();
		if(null == lstPoolBookingUsages || lstPoolBookingUsages.isEmpty()){
			return 0;
		}
		return lstPoolBookingUsages.size();
		
	}
	
	@Override
	@Transactional
	public String insertPoolBookingUsages(PoolBookingUsages poolBookingUsages) {

		// inserts into database & return bookId (primary_key)
		int poolBookingUsagesId = (Integer) sessionFactory.getCurrentSession().save(poolBookingUsages);
		return "" + poolBookingUsagesId;
	}

}
