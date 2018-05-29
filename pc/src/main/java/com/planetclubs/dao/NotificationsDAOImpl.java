package com.planetclubs.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.planetclubs.model.Notifications;
import com.planetclubs.model.Services;

@Repository("notificationsDAO")
public class NotificationsDAOImpl implements NotificationsDAO{

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
	public List<Notifications> getAllNotificationsById(String field, int id) {
		List<Notifications> lstNotifications = sessionFactory.getCurrentSession().createCriteria(Notifications.class).add(Restrictions.eq(field, id)).list();
		
		return lstNotifications;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public String createOrSaveNotifications(Notifications notification) {
		int id = (Integer) sessionFactory.getCurrentSession().save(notification);
		return "" + id;
	}

}
