package com.planetclubs.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.planetclubs.model.Coupons;
import com.planetclubs.model.ReferenceData;

@Repository("generalDAO")
public class GeneralDAOImpl implements GeneralDAO{

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ReferenceData> getReferenceData() {
		
		List<ReferenceData> lstreferenceData = sessionFactory.getCurrentSession().createCriteria(ReferenceData.class).list();
		
		return lstreferenceData;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Coupons checkCouponCode(String couponCode) {
		Date now = new Date();
		
		Calendar endCal = Calendar.getInstance();
		
		endCal.setTime(now);
		endCal.setTimeZone(TimeZone.getTimeZone("IST"));
	
		List<Coupons> couponsList = sessionFactory.getCurrentSession().createCriteria(Coupons.class).add(Restrictions.eq("couponCode", couponCode))
				.add(Restrictions.lt("validFrom", endCal.getTime())).add(Restrictions.ge("validTo", endCal.getTime())).add(Restrictions.eq("isLive", true)).list();
		if(couponsList.isEmpty()){
			return null;
		}
		return couponsList.get(0);
		
	}

}
