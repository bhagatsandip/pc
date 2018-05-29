package com.planetclubs.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.planetclubs.model.Ratings;
import com.planetclubs.model.Services;
import com.planetclubs.model.Usage;

@Repository("ratingsDAO")
public class RatingsDAOImpl implements RatingsDAO{
	
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
	public List<Ratings> getAllRatingsByClubId(String field, int id) {
		List<Ratings> lstRatings = sessionFactory.getCurrentSession().createCriteria(Ratings.class).add(Restrictions.eq(field, id)).list();
		
		return lstRatings;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public double getAvgRatingsByClubId(int id) {
		
		String SQL_QUERY = "select avg(rating) from Ratings where clubId="+id;
		List<Double> ratingLst =  sessionFactory.getCurrentSession().createQuery(SQL_QUERY).list();
		
		if(ratingLst.size()>0 && ratingLst.get(0)!=null){
			return ratingLst.get(0);
		}
		
		return 0;
	}

	@Override
	@Transactional
	public void rateClub(Ratings ratings) {
		sessionFactory.getCurrentSession().save(ratings);
	}
	
	@Override
	@Transactional
	public void updateClubsRatings(Ratings ratings) {

		sessionFactory.getCurrentSession().update(ratings);
	}

}
