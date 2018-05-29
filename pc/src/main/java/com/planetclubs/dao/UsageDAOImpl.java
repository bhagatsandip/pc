package com.planetclubs.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.planetclubs.model.Membership;
import com.planetclubs.model.Usage;

@Repository("usageDAO")
public class UsageDAOImpl implements UsageDAO {

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
	public String insertNewUsageInfo(Usage usage) {

		int usageId = (Integer) sessionFactory.getCurrentSession().save(usage);
		return "" + usageId;
	}

	@Override
	@Transactional
	public Usage getUsageInfo(int userId) {

		Usage usage = (Usage) sessionFactory.getCurrentSession().get(Usage.class, userId);
		System.out.println(usage);
		return usage;
	}

	@Override
	@Transactional
	public String updateUsageInfo(Usage updateUsage) {

		sessionFactory.getCurrentSession().update(updateUsage);
		return "Usage information updated successfully";
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Usage> getAllUsageInfo(String field, int id) {

		
		List<Usage> lstUsage = sessionFactory.getCurrentSession().createCriteria(Usage.class).add(Restrictions.eq(field, id)).list();
		
		return lstUsage;
	}
}