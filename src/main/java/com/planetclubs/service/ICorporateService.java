package com.planetclubs.service;

import java.util.Map;

import javax.ws.rs.PathParam;

import in.planetclubs.CorporateListType;
import in.planetclubs.GetPoolMembershipRequestType;

public interface ICorporateService {
	
	public CorporateListType getAllCorporatesInfo();
	public boolean checkIfEmailValid(String emailId,int membershipid);
	public Map<String,Object> verifyOTP(int otp,String emailId,
			String membershipNo);
	public Map<String, Integer> getCorporateAmount(int id,String membershipId);
		
	public String getPoolMembership(GetPoolMembershipRequestType getPoolMenbershipRequestType);
}
