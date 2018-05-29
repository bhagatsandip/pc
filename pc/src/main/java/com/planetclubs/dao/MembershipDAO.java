package com.planetclubs.dao;

import java.util.List;

import com.planetclubs.model.Membership;
import com.planetclubs.model.PCMembership;

public interface MembershipDAO {

	public String insertNewMembershipInfo(Membership membership);
	public Membership getMembershipInfo(int id);
	public String updateMembershipInfo(Membership membership);
	public String insertNewPCMembershipInfo(PCMembership pcMembership);
	public List<PCMembership> getPCMembershipInfo(int userId);
	public String updatePCMembershipInfo(PCMembership pcMembership);
	public PCMembership getActivePCMembership(int userId);
	List<Membership> getAllMembershipByField(String field, String value);
	Membership getMembershipInfoById(String field, String value);
	Membership getMembershipInfoById(String field, int value);
	public List<PCMembership> getPCMembershipInfo(int userId, int membershipId);
	public List<PCMembership> getActivePCMembershipByClubId(int clubId);
}