package com.planetclubs.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.planetclubs.model.Transaction;

@Repository("transactionDAO")
public class TransactionsDAOImpl implements TransactionDAO{

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
	public String createOrSaveTransactions(Transaction transaction) {
		int id = (Integer) sessionFactory.getCurrentSession().save(transaction);
		return "" + id;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public String updateTransaction(Transaction transaction) {
		// update database with membership information and return success msg
		sessionFactory.getCurrentSession().update(transaction);
		return "Transactipn information updated successfully";
	}

}
