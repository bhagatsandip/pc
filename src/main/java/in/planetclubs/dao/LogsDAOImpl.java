package com.planetclubs.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.planetclubs.model.Logs;

@Repository("logsDAO")
public class LogsDAOImpl implements LogsDAO {

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
	public void insertNewLogs(Logs log) {

		// inserts into database & return clubId (primary_key)
		sessionFactory.getCurrentSession().save(log);
		
	}

}