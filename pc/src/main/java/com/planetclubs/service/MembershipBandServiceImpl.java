package com.planetclubs.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.planetclubs.dao.MembershipBandDAO;
import com.planetclubs.model.Clubs;
import com.planetclubs.model.MembershipBand;

import in.planetclubs.ClubListType;
import in.planetclubs.ClubType;
import in.planetclubs.MembershipBandListType;
import in.planetclubs.MembershipBandType;

@Component
@Path("/membershipbandservice")
public class MembershipBandServiceImpl implements IMembershipBandService {

	@Autowired
	private MembershipBandDAO membershipBandDAO;


	@GET
	@Path("getmembershipband/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public MembershipBandType getMembershipBandInfo(@PathParam("id") int id) {

		MembershipBand membershipBand = membershipBandDAO.getMembershipBandInfo(id);

		MembershipBandType membershipBandType = new MembershipBandType();
		
		membershipBandType.setId(membershipBand.getId());
		membershipBandType.setMembershipType(membershipBand.getMembershipType());
		membershipBandType.setHalfYearly(membershipBand.getHalfYearly());
		membershipBandType.setMonthly(membershipBand.getMonthly());
		membershipBandType.setQuarterly(membershipBand.getQuarterly());
		
		return membershipBandType;
	}
	
	@GET
	@Path("getallmembershipband")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public MembershipBandListType getAllMembershipBandInfo() {

		List<MembershipBand> lstmembershipBand = membershipBandDAO.getAllMembershipBandInfo();

		MembershipBandListType membershipBandListType = new MembershipBandListType();

		for(MembershipBand membershipBand : lstmembershipBand){
			MembershipBandType membershipBandType = new MembershipBandType();
			

			membershipBandType.setId(membershipBand.getId());
			membershipBandType.setMembershipType(membershipBand.getMembershipType());
			membershipBandType.setHalfYearly(membershipBand.getHalfYearly());
			membershipBandType.setMonthly(membershipBand.getMonthly());
			membershipBandType.setQuarterly(membershipBand.getQuarterly());
			
			membershipBandListType.getmembershipBandType().add(membershipBandType);
		}
		return membershipBandListType;
	}

}