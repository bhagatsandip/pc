package com.planetclubs.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.planetclubs.model.ClubsPoolBooking;
import com.planetclubs.model.ClubsPoolBookingMap;

@Repository("clubsPoolBookingMapDAO")
public class ClubsPoolBookingMapDAOImpl implements ClubsPoolBookingMapDAO{

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
	public List<ClubsPoolBookingMap> getClubsPoolBookingMapById(int clubsPoolBookingId) {
		List<ClubsPoolBookingMap> lstClubsPoolBookingMap = sessionFactory.getCurrentSession().createCriteria(ClubsPoolBookingMap.class).add(Restrictions.eq("clubsPoolBookingId", clubsPoolBookingId)).list();
		if(null == lstClubsPoolBookingMap || lstClubsPoolBookingMap.isEmpty()){
			return null;
		}
		return lstClubsPoolBookingMap;
	}

	@Override
	@Transactional
	public boolean checkServiceById(int serviceId) {
		List<ClubsPoolBookingMap> lstService = sessionFactory.getCurrentSession().createCriteria(ClubsPoolBookingMap.class).add(Restrictions.eq("serviceId", serviceId)).list();
		if(null == lstService || lstService.isEmpty()){
			return false;
		}
		return lstService.size()>0 ? true: false;
	}

}
