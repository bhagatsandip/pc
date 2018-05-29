package com.planetclubs.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.planetclubs.model.ClubsPoolBooking;
import com.planetclubs.model.CorporatePlans;

@Repository("clubsPoolBookingDAO")
public class ClubsPoolBookingDAOImpl implements ClubsPoolBookingDAO{

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
	public List<ClubsPoolBooking> getClubsPoolBookingById(int corporatePlanId) {
		List<ClubsPoolBooking> lstClubsPoolBooking = sessionFactory.getCurrentSession().createCriteria(ClubsPoolBooking.class).add(Restrictions.eq("corporatePlanId", corporatePlanId)).list();
		if(null == lstClubsPoolBooking || lstClubsPoolBooking.isEmpty()){
			return null;
		}
		return lstClubsPoolBooking;
	}

}
