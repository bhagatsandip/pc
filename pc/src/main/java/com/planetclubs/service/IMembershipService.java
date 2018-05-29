package com.planetclubs.service;

import in.planetclubs.MembershipTransanctionType;
import in.planetclubs.MembershipType;

public interface IMembershipService {

	// Basic CRUD operations for Book Service

	public String createOrSaveMembershipInfo(MembershipType membershipType);
	public MembershipType getMembershipInfo(int membershipId);
	public String updateMembershipInfo(MembershipType membershipType);
	public MembershipType createOrSaveMembershipTypeInfo(MembershipTransanctionType membershipTranType);
	
}