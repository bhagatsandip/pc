package com.planetclubs.service;


import java.util.Map;

import in.planetclubs.PCMembershipClubListType;
import in.planetclubs.PCMembershipListType;
import in.planetclubs.PCMembershipType;

public interface IPCMembershipService {

	// Basic CRUD operations for Book Service

	public String createOrSavePCMembership(PCMembershipType pcMbershipType);
	public PCMembershipListType getPCMembershipInfo(int userId);
	public String updatePCMembershipInfo(PCMembershipType pcMembershipType);
	public PCMembershipType getActivePCMembership(int userId);
	public Map<String,Object> getMembershipData(int userId);
	public PCMembershipClubListType getActivePCMembershipByClubId(int clubId);
	
}