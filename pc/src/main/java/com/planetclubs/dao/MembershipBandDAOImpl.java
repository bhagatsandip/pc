package com.planetclubs.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.planetclubs.model.Clubs;
import com.planetclubs.model.Membership;
import com.planetclubs.model.MembershipBand;
import com.planetclubs.model.PCMembership;

@Repository("membershipBandDAO")
public class MembershipBandDAOImpl implements MembershipBandDAO {

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
	public MembershipBand getMembershipBandInfo(int id) {

		List<MembershipBand> membership = sessionFactory.getCurrentSession().createCriteria(Membership.class).add(Restrictions.eq("id", id)).list();;
				
		return membership.get(0);
	}

	@Override
	@Transactional
	public List<MembershipBand> getAllMembershipBandInfo() {
		List<MembershipBand> lstmembershipBand = sessionFactory.getCurrentSession().createCriteria(MembershipBand.class).list();
		return lstmembershipBand;
	}

}