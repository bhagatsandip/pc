package com.planetclubs.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.planetclubs.model.CorporatePlans;

@Repository("corporatePlansDAO")
public class CorporatePlansDAOImpl implements CorporatePlansDAO{

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
	public List<CorporatePlans> getCorporatePlanById(int corporateId) {
		List<CorporatePlans> lstCorporatePlans = sessionFactory.getCurrentSession().createCriteria(CorporatePlans.class).add(Restrictions.eq("isValid", true)).list();
		if(null == lstCorporatePlans || lstCorporatePlans.isEmpty()){
			return null;
		}
		return lstCorporatePlans;
	}

}
