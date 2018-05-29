package com.planetclubs.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.planetclubs.model.Services;
import com.planetclubs.model.ServicesDetails;

@Repository("servicesDAO")
public class ServicesDAOImpl implements ServicesDAO{

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
	public List<Services> getAllServicesByClubId(String field, int id) {
		List<Services> lstServices = sessionFactory.getCurrentSession().createCriteria(Services.class).add(Restrictions.eq(field, id)).list();
		
		return lstServices;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ServicesDetails> getAllServicesDetailsByServiceId(String field, int id) {
		List<ServicesDetails> lstServices = sessionFactory.getCurrentSession().createCriteria(ServicesDetails.class).add(Restrictions.eq(field, id)).list();
		
		return lstServices;
	}

	@Override
	@Transactional
	public String insertNewService(ServicesDetails servicesDetails) {

		// inserts into database & return bookId (primary_key)
		int servicesDetailsId = (Integer) sessionFactory.getCurrentSession().save(servicesDetails);
		return "Success. Id: "+ servicesDetailsId;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public int getClubByServiceId(int serviceId) {
		List<Services> lstServices = sessionFactory.getCurrentSession().createCriteria(Services.class).add(Restrictions.eq("serviceId", serviceId)).list();
		return lstServices.get(0).getClubId();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Services getServiceByServiceId(int serviceId) {
		List<Services> lstServices = sessionFactory.getCurrentSession().createCriteria(Services.class).add(Restrictions.eq("serviceId", serviceId)).list();
		return lstServices.get(0);
	}
	
}
