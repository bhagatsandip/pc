package com.planetclubs.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.planetclubs.model.Membership;
import com.planetclubs.model.PCMembership;

@Repository("membershipDAO")
public class MembershipDAOImpl implements MembershipDAO {

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
	public String insertNewMembershipInfo(Membership membership) {

		// inserts into database & return membershipId (primary_key)
		int id = (Integer) sessionFactory.getCurrentSession().save(membership);
		return "" + id;
	}

	@Override
	@Transactional
	public Membership getMembershipInfo(int id) {

		// retrieve membership object based on the id supplied in the formal argument
		List<Membership> membership = sessionFactory.getCurrentSession().createCriteria(Membership.class).add(Restrictions.eq("userId", id)).add(Restrictions.eq("active",1)).list();
		if(null == membership || membership.isEmpty()){
			return null;
		}		
		return membership.get(0);
	}
	
	@Override
	@Transactional
	public List<Membership> getAllMembershipByField(String field,String value) {
		List<Membership> membership = sessionFactory.getCurrentSession().createCriteria(Membership.class).add(Restrictions.eq(field, value)).list();
		return membership;
	}
	
	@Override
	@Transactional
	public Membership getMembershipInfoById(String field,String value) {

		// retrieve membership object based on the id supplied in the formal argument
		List<Membership> membership = sessionFactory.getCurrentSession().createCriteria(Membership.class).add(Restrictions.eq(field, value)).add(Restrictions.eq("active",1)).list();
				
		return membership.get(0);
	}
	
	@Override
	@Transactional
	public Membership getMembershipInfoById(String field,int value) {

		// retrieve membership object based on the id supplied in the formal argument
		List<Membership> membership = sessionFactory.getCurrentSession().createCriteria(Membership.class).add(Restrictions.eq(field, value)).add(Restrictions.eq("active",1)).list();
				
		return membership.get(0);
	}

	@Override
	@Transactional
	public String updateMembershipInfo(Membership membership) {

		// update database with membership information and return success msg
		sessionFactory.getCurrentSession().update(membership);
		return "Membership information updated successfully";
	}

	@Override
	@Transactional
	public String insertNewPCMembershipInfo(PCMembership pcMembership) {
		int id = (Integer) sessionFactory.getCurrentSession().save(pcMembership);
		return "" + id;
	}

	@Override
	@Transactional
	public List<PCMembership> getPCMembershipInfo(int userId) {
		// retrieve membership object based on the id supplied in the formal argument
		List<PCMembership> pcMembership = sessionFactory.getCurrentSession().createCriteria(PCMembership.class).add(Restrictions.eq("userId", userId)).list();
				
		return pcMembership;
	}
	
	@Override
	@Transactional
	public List<PCMembership> getPCMembershipInfo(int userId, int membershipId) {
		// retrieve membership object based on the id supplied in the formal argument
		List<PCMembership> pcMembership = sessionFactory.getCurrentSession().createCriteria(PCMembership.class).add(Restrictions.eq("userId", userId)).add(Restrictions.eq("membershipId",membershipId)).list();
				
		return pcMembership;
	}

	@Override
	@Transactional
	public String updatePCMembershipInfo(PCMembership pcMembership) {
		sessionFactory.getCurrentSession().update(pcMembership);
		return "PCMembership information updated successfully";
	}

	@Override
	@Transactional
	public PCMembership getActivePCMembership(int userId) {

		// retrieve membership object based on the id supplied in the formal argument
		List<PCMembership> pcmembership = sessionFactory.getCurrentSession().createCriteria(PCMembership.class).add(Restrictions.eq("userId", userId)).add(Restrictions.eq("active",true)).list();;
		if(null == pcmembership || pcmembership.isEmpty()){
			return null;
		}
		return pcmembership.get(0);

	}
	
	@Override
	@Transactional
	public List<PCMembership> getActivePCMembershipByClubId(int clubId) {

		// retrieve membership object based on the id supplied in the formal argument
		List<PCMembership> pcmembership = sessionFactory.getCurrentSession().createCriteria(PCMembership.class).add(Restrictions.eq("clubId", clubId)).list();;
		return pcmembership;

	}


}