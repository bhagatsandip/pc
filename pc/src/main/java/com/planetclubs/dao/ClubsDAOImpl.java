package com.planetclubs.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.planetclubs.model.ClubPromotions;
import com.planetclubs.model.Clubs;

@Repository("clubDAO")
public class ClubsDAOImpl implements ClubsDAO {

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
	public String insertNewClubInfo(Clubs club) {

		// inserts into database & return clubId (primary_key)
		int clubId = (Integer) sessionFactory.getCurrentSession().save(club);
		return "Club information saved successfully with Club_ID " + clubId;
	}

	@Override
	@Transactional
	public Clubs getClubInfo(int clubId) {

		// retrieve club object based on the id supplied in the formal argument
		Clubs club = (Clubs) sessionFactory.getCurrentSession().get(Clubs.class, clubId);
		return club;
	}

	@Override
	@Transactional
	public String updateClubInfo(Clubs clubs) {

		// update database with club information and return success msg
		sessionFactory.getCurrentSession().update(clubs);
		return "Club information updated successfully";
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Clubs> getAllClubInfo() {

		// get all clubs info from database
		List<Clubs> lstClubs = sessionFactory.getCurrentSession().createCriteria(Clubs.class).add(Restrictions.ne("id", 1)).list();
		return lstClubs;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Clubs getClubInfoByClubUserId(int userId) {
		// retrieve club object based on the id supplied in the formal argument
		List<Clubs> club = sessionFactory.getCurrentSession().createCriteria(Clubs.class).add(Restrictions.eq("userId", userId)).list();
		if(!club.isEmpty()){
			return club.get(0);
		}
		else {
			return new Clubs();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ClubPromotions> getClubPromotions(String location) {
		
		Date now = new Date();
		
		Calendar endCal = Calendar.getInstance();
		
		endCal.setTime(now);
		endCal.setTimeZone(TimeZone.getTimeZone("IST"));
		
		List<String> locations = new ArrayList<String>();
		locations.add("india");
		locations.add(location);

		//Query query = sessionFactory.getCurrentSession().createQuery("FROM clubpromotions cp WHERE cp.location IN (location,  'india') AND cp.fromDate <  now AND cp.toDate >  now AND live = true");
		List<ClubPromotions> clubs = (List<ClubPromotions>) sessionFactory.getCurrentSession().createCriteria(ClubPromotions.class).add(Restrictions.in("location", locations))
				.add(Restrictions.lt("fromDate", endCal.getTime())).add(Restrictions.gt("toDate", endCal.getTime())).add(Restrictions.eq("live", true)).list();
		
		return clubs;
		
		
	}
}