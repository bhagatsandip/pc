package com.planetclubs.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.planetclubs.model.Notifications;
import com.planetclubs.model.Tokens;

@Repository("tokensDAO")
public class TokensDAOImpl implements TokensDAO{
	
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
	public Tokens getTokenByUserId(String field, int id) {
		List<Tokens> lstTokens = sessionFactory.getCurrentSession().createCriteria(Tokens.class).add(Restrictions.eq(field, id)).list();
		
		return lstTokens.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public String createToken(Tokens token) {
		sessionFactory.getCurrentSession().saveOrUpdate(token);
		return "" + token.getUserId();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public String updateToken(Tokens token) {
		sessionFactory.getCurrentSession().update(token);
		return "Token Updated Successfully.";
	}

}
