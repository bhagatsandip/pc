package com.planetclubs.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.planetclubs.model.Membership;
import com.planetclubs.model.Payments;
import com.planetclubs.model.Usage;

@Repository("paymentDAO")
public class PaymentsDAOImpl implements PaymentDAO {

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
	public List<Payments> getAllPayments(String field, int id) {

		List<Payments> lstPayments = sessionFactory.getCurrentSession().createCriteria(Payments.class).add(Restrictions.eq(field, id)).list();
		
		return lstPayments;
	}
}