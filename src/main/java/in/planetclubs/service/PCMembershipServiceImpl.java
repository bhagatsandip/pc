package com.planetclubs.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.planetclubs.dao.MembershipDAO;
import com.planetclubs.dao.UserDAO;
import com.planetclubs.model.Membership;
import com.planetclubs.model.PCMembership;
import com.planetclubs.model.Users;
import com.planetclubs.util.Utils;

import in.planetclubs.MembershipType;
import in.planetclubs.PCMembershipClubListType;
import in.planetclubs.PCMembershipClubType;
import in.planetclubs.PCMembershipListType;
import in.planetclubs.PCMembershipType;
import in.planetclubs.UserType;

@Component
@Path("/pcmembershipservice")
public class PCMembershipServiceImpl implements IPCMembershipService {

	@Autowired
	private MembershipDAO membershipDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	Utils util;


	@POST
	@Path("addpcmembership")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String createOrSavePCMembership(PCMembershipType pcMembershipType) {

		
		PCMembership pcMembership = new PCMembership();
		pcMembership.setUserId(pcMembershipType.getUserId());
		pcMembership.setClubId(pcMembershipType.getClubId());
		pcMembership.setServiceId(pcMembershipType.getServiceId());
		Date start = util.getCurrentTime();
		Date end = util.addMonths(1);
		pcMembership.setStartTime(start);
		pcMembership.setEndTime(end);
		pcMembership.setActive(pcMembershipType.isActive());
		pcMembership.setMembershipId(pcMembershipType.getMembershipId());
		pcMembership.setAutoRenew(pcMembershipType.isAutoRenew());
		pcMembership.setEnrollmentsUsed(pcMembershipType.getEnrollmentsUsed());
		
		return membershipDAO.insertNewPCMembershipInfo(pcMembership);
	}

	@GET
	@Path("getpcmembership/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public PCMembershipListType getPCMembershipInfo(@PathParam("id") int userId) {

		List<PCMembership> pcMembershipLst = membershipDAO.getPCMembershipInfo(userId);

		PCMembershipListType pcMembershipListType = new PCMembershipListType();
		
		for(PCMembership pcMembership : pcMembershipLst){
		PCMembershipType pcMembershipType = new PCMembershipType();
		pcMembershipType.setId(pcMembership.getId());
		pcMembershipType.setUserId(pcMembership.getUserId());
		pcMembershipType.setClubId(pcMembership.getClubId());
		pcMembershipType.setServiceId(pcMembership.getServiceId());
		pcMembershipType.setStartTime(pcMembership.getStartTime());
		pcMembershipType.setEndTime(pcMembership.getEndTime());
		pcMembershipType.setActive(pcMembership.isActive());
		pcMembershipType.setMembershipId(pcMembership.getMembershipId());
		pcMembershipType.setAutoRenew(pcMembership.isAutoRenew());
		pcMembershipType.setEnrollmentsUsed(pcMembership.getEnrollmentsUsed());
		
		pcMembershipListType.getPCMembershipType().add(pcMembershipType); 
		
		
		}
		
		return pcMembershipListType;
	}

	@POST
	@Path("updatemembership")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String updatePCMembershipInfo(PCMembershipType pcMembershipType) {

		PCMembership pcMembership = new PCMembership();
		pcMembership.setId(pcMembershipType.getId());
		pcMembership.setUserId(pcMembershipType.getUserId());
		pcMembership.setClubId(pcMembershipType.getClubId());
		pcMembership.setServiceId(pcMembershipType.getServiceId());
		pcMembership.setStartTime(pcMembershipType.getStartTime());
		pcMembership.setEndTime(pcMembershipType.getEndTime());
		pcMembership.setActive(pcMembershipType.isActive());
		pcMembership.setMembershipId(pcMembershipType.getMembershipId());
		pcMembership.setAutoRenew(pcMembershipType.isAutoRenew());
		pcMembership.setEnrollmentsUsed(pcMembershipType.getEnrollmentsUsed());

		return membershipDAO.updatePCMembershipInfo(pcMembership);
	}

	@GET
	@Path("getactivepcmembership/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public PCMembershipType getActivePCMembership(int userId) {
		
		PCMembership pcMembership = membershipDAO.getActivePCMembership(userId);
		if(null != pcMembership){
			
				PCMembershipType pcMembershipType = new PCMembershipType();
				pcMembershipType.setId(pcMembership.getId());
				pcMembershipType.setUserId(pcMembership.getUserId());
				pcMembershipType.setClubId(pcMembership.getClubId());
				pcMembershipType.setServiceId(pcMembership.getServiceId());
				pcMembershipType.setStartTime(pcMembership.getStartTime());
				pcMembershipType.setEndTime(pcMembership.getEndTime());
				pcMembershipType.setActive(pcMembership.isActive());
				pcMembershipType.setMembershipId(pcMembership.getMembershipId());
				pcMembershipType.setAutoRenew(pcMembership.isAutoRenew());
				pcMembershipType.setEnrollmentsUsed(pcMembership.getEnrollmentsUsed());
				
				return pcMembershipType;
				
		}
				
		return null;

	}
	
	@GET
	@Path("getactivepcmembershipbyclubid/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public PCMembershipClubListType getActivePCMembershipByClubId(@PathParam("id") int clubId) {
		
		List<PCMembership> pcMembershipLst = membershipDAO.getActivePCMembershipByClubId(clubId);
		PCMembershipClubListType pcMembershipClubListType = new PCMembershipClubListType();
		if(pcMembershipLst.size() > 0){
		
			for (Iterator<PCMembership> iterator = pcMembershipLst.iterator(); iterator.hasNext();) {
				PCMembership pcMembership = iterator.next();
	
				PCMembershipClubType pcMembershipType = new PCMembershipClubType();
				pcMembershipType.setId(pcMembership.getId());
				pcMembershipType.setUserId(pcMembership.getUserId());
				pcMembershipType.setClubId(pcMembership.getClubId());
				pcMembershipType.setServiceId(pcMembership.getServiceId());
				pcMembershipType.setStartTime(pcMembership.getStartTime());
				pcMembershipType.setEndTime(pcMembership.getEndTime());
				pcMembershipType.setActive(pcMembership.isActive());
				pcMembershipType.setMembershipId(pcMembership.getMembershipId());
				pcMembershipType.setAutoRenew(pcMembership.isAutoRenew());
				pcMembershipType.setEnrollmentsUsed(pcMembership.getEnrollmentsUsed());
				Users  getUser = userDAO.getUserInfo(pcMembership.getUserId());
				UserType userType = new UserType();
				userType.setUserId(getUser.getUserId());
				userType.setContactNo(getUser.getContactNo());
				userType.setEmail(getUser.getEmail());
				userType.setFirstName(getUser.getFirstName());
				userType.setLastName(getUser.getLastName());
				userType.setPassword(getUser.getPassword());
				userType.setUserType(getUser.getUserType());
				userType.setVerified(getUser.isVerified());
				userType.setCompVerified(getUser.isCompVerified());
				userType.setToken(getUser.getToken());
				userType.setCorporateId(getUser.getCorporateId());
				userType.setReferalCode(getUser.getReferalCode());
				
				pcMembershipType.setUserType(userType);
				
				pcMembershipClubListType.getPCMembershipType().add(pcMembershipType);
				
				
				}
			
		}
		return pcMembershipClubListType;

	}
	
	@GET
	@Path("getmembershipdata/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Map<String,Object> getMembershipData(@PathParam("id") int userId) {

		Map<String,Object> map = new HashMap<>();

		
		Membership membership = membershipDAO.getMembershipInfo(userId);
		MembershipType membershipType = new MembershipType();
		membershipType.setMembershipId(membership.getMembershipId());
		membershipType.setMembershipNo(membership.getMembershipNo());
		membershipType.setTotalAddonPoints(membership.getTotalAddonPoints());
		membershipType.setStartDate(membership.getStartDate());
		membershipType.setEndDate(membership.getEndDate());
		membershipType.setUpdateDate(membership.getUpdateDate());
		membershipType.setUserId(membership.getUserId());
		membershipType.setMembershipType(membership.getMembershipType());
		membershipType.setActive(membership.isActive());
		membershipType.setChunks(membership.getChunks());
		membershipType.setCorporateId(membership.getCorporateId());
		membershipType.setCorporateAmount(membership.getCorporateAmount());
		membershipType.setCorporateEmail(membership.getCorporateEmail());
		membershipType.setCouponCode(membership.getCouponCode());
		membershipType.setMembershipAmount(membership.getMembershipAmount());
		
		
		List<PCMembership> pcMembershipLst = membershipDAO.getPCMembershipInfo(userId);

		PCMembershipListType pcMembershipListType = new PCMembershipListType();
		
		for(PCMembership pcMembership : pcMembershipLst){
		PCMembershipType pcMembershipType = new PCMembershipType();
		pcMembershipType.setId(pcMembership.getId());
		pcMembershipType.setUserId(pcMembership.getUserId());
		pcMembershipType.setClubId(pcMembership.getClubId());
		pcMembershipType.setServiceId(pcMembership.getServiceId());
		pcMembershipType.setStartTime(pcMembership.getStartTime());
		pcMembershipType.setEndTime(pcMembership.getEndTime());
		pcMembershipType.setActive(pcMembership.isActive());
		pcMembershipType.setMembershipId(pcMembership.getMembershipId());
		pcMembershipType.setAutoRenew(pcMembership.isAutoRenew());
		pcMembershipType.setEnrollmentsUsed(pcMembership.getEnrollmentsUsed());
		
		pcMembershipListType.getPCMembershipType().add(pcMembershipType); 
		
		}
		
		map.put("membership",membershipType);
		map.put("pcMembershipList", pcMembershipListType);
		
		return map;
	}


}