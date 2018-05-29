package com.planetclubs.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.planetclubs.model.Corporate;
import com.planetclubs.model.CorporatePlans;

@Repository("corporateDAO")
public class CorporateDaoImpl implements CorporateDAO {

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
	public List<Corporate> getAllCorporatesInfo() {
		List<Corporate> lstCorporates = sessionFactory.getCurrentSession().createCriteria(Corporate.class).list();
		return lstCorporates;
	}

	@Override
	@Transactional
	public int getCorporateById(int id) {
		List<CorporatePlans> corporateLst = sessionFactory.getCurrentSession().createCriteria(CorporatePlans.class).add(Restrictions.eq("corporateId", id)).add(Restrictions.eq("isValid",true)).list();
		if(corporateLst!=null && !corporateLst.isEmpty())
			return corporateLst.get(0).getAmount();
		
		return 0;
	}

	@Override
	@Transactional
	public Corporate getCorporate(int id) {
		List<Corporate> corporateLst = sessionFactory.getCurrentSession().createCriteria(Corporate.class).add(Restrictions.eq("id", id)).list();
		if(corporateLst!=null && !corporateLst.isEmpty())
			return corporateLst.get(0);
		
		return null;
	}

}