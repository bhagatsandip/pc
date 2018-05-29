package com.planetclubs.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.planetclubs.dao.ClubsPoolBookingDAO;
import com.planetclubs.dao.ClubsPoolBookingMapDAO;
import com.planetclubs.dao.CorporateDAO;
import com.planetclubs.dao.CorporatePlansDAO;
import com.planetclubs.dao.MembershipDAO;
import com.planetclubs.dao.PoolBookingUsagesDAO;
import com.planetclubs.model.ClubsPoolBooking;
import com.planetclubs.model.ClubsPoolBookingMap;
import com.planetclubs.model.Corporate;
import com.planetclubs.model.CorporatePlans;
import com.planetclubs.model.Membership;
import com.planetclubs.util.SendEmail;

import in.planetclubs.CorporateListType;
import in.planetclubs.CorporateType;
import in.planetclubs.GetPoolMembershipRequestType;

@Component
@Path("/corporateservice")
public class CorporateServiceImpl implements ICorporateService{

	@Autowired
	private CorporateDAO corporateDAO;

	@Autowired
	private MembershipDAO membershipDAO;

	@Autowired
	private CorporatePlansDAO corporatePlansDAO;

	@Autowired
	private ClubsPoolBookingDAO clubsPoolBookingDAO;

	@Autowired
	private ClubsPoolBookingMapDAO clubsPoolBookingMapDAO;

	@Autowired
	private PoolBookingUsagesDAO poolBookingUsagesDAO;

	@GET
	@Path("getallcorporates")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public CorporateListType getAllCorporatesInfo() {

		List<Corporate> lstCorporate = corporateDAO.getAllCorporatesInfo();

		CorporateListType corporateListType = new CorporateListType();

		for(Corporate corporate : lstCorporate){
			CorporateType corporateType = new CorporateType();

			corporateType.setId(corporate.getId());
			corporateType.setAddress(corporate.getAddress());
			corporateType.setContactEmail(corporate.getContactEmail());
			corporateType.setContactNo(corporate.getContactNo());
			corporateType.setEmailDomain(corporate.getEmailDomain());
			corporateType.setName(corporate.getName());
			corporateType.setDoubleVerification(corporate.isDoubleVerification());

			corporateListType.getCorporateType().add(corporateType); // add to bookListType
		}
		return corporateListType;
	}

	@GET
	@Path("checkIfEmailValid/{emailId}/{membershipId}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public boolean checkIfEmailValid(@PathParam("emailId") String emailId,
			@PathParam("membershipId") int membershipId) {


		List<Corporate> lstCorporate = corporateDAO.getAllCorporatesInfo();
		int index = emailId.indexOf('@');
		String domain = emailId.substring(index,emailId.length());
		SendEmail emailSender = new SendEmail();
		for (Iterator<Corporate> iterator = lstCorporate.iterator(); iterator.hasNext();) {
			Corporate corporate = iterator.next();
			List<String> domainList = Arrays.asList(corporate.getEmailDomain().split(","));
			for (String dom: domainList){
				if(domain.equalsIgnoreCase(dom)){
					Random rnd = new Random();
					int otp = 100000 + rnd.nextInt(900000);

					emailSender.sendCorporateOTP(emailId,otp);
					Membership membership = membershipDAO.getMembershipInfoById("membershipId",membershipId);
					membershipDAO.updateMembershipInfo(membership);
					return true;
				}

			}
		}

		return false;
	}
	
	
	@GET
	@Path("getCorporateAmount/{id}/{membershipId}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Map<String,Integer> getCorporateAmount(@PathParam("id") int id,@PathParam("membershipId") String membershipId) {
		
		int amount  = corporateDAO.getCorporateById(id);
		Map<String,Integer> map = new HashMap<>();
		List<Membership> membershipList = membershipDAO.getAllMembershipByField("membershipNo",membershipId);
		int totalCorpAmount = 0;
		

		for(Membership membershp: membershipList){
			if(membershp.getCorporateId() == id)
				totalCorpAmount += membershp.getCorporateAmount();
		}
		map.put("corporateAmount", amount);
		map.put("amountUsed", totalCorpAmount);
		return map;
	}

	
	@GET
	@Path("verifyOTP/{otp}/{email}/{membershipId}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Map<String,Object> verifyOTP(@PathParam("otp") int otp,@PathParam("email") String emailId,
			@PathParam("membershipId") String membershipId) {


		List<Corporate> lstCorporate = corporateDAO.getAllCorporatesInfo();
		int index = emailId.indexOf('@');
		String domain = emailId.substring(index,emailId.length());
		Map<String,Object> map = new HashMap<>();
		List<Membership> membershipList = membershipDAO.getAllMembershipByField("membershipNo", membershipId);
		int totalCorpAmount =0;

		Membership activeMembership = membershipDAO.getMembershipInfoById("membershipNo", membershipId); 
		for (Iterator<Corporate> iterator = lstCorporate.iterator(); iterator.hasNext();) {
			Corporate corporate = iterator.next();
			List<String> domainList = Arrays.asList(corporate.getEmailDomain().split(","));
			for (String dom: domainList){
				if(domain.equalsIgnoreCase(dom)){
					membershipDAO.updateMembershipInfo(activeMembership);

					for(Membership membershp: membershipList){
						//if(today.after(corporate.getValidFrom()) && today.before(corporate.getValidTo())){ // Todo : to be modified. 
						totalCorpAmount += membershp.getCorporateAmount();

						//}
					}
					map.put("Membership", activeMembership);
					map.put("corporate", corporate);
					map.put("corpAmount", totalCorpAmount);

					return map;
				}

			}
		}

		return map;
	}

	@POST
	@Path("getpoolmembership")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String getPoolMembership(GetPoolMembershipRequestType getPoolMembershipRequestType) {

		if(clubsPoolBookingMapDAO.checkServiceById(getPoolMembershipRequestType.getServiceId())){

			List<CorporatePlans> corporatePlansList = corporatePlansDAO.getCorporatePlanById(getPoolMembershipRequestType.getCorporateId());

			if(corporatePlansList == null){
				return "0";
			}
			for (Iterator<CorporatePlans> iterator = corporatePlansList.iterator(); iterator.hasNext();) {
				CorporatePlans corporatePlans = (CorporatePlans) iterator.next();

				List<ClubsPoolBooking> clubPoolBookingList = clubsPoolBookingDAO.getClubsPoolBookingById(corporatePlans.getId());
				if(clubPoolBookingList == null){
					return "0";
				}

				for (Iterator<ClubsPoolBooking> iterator2 = clubPoolBookingList.iterator(); iterator2.hasNext();) {
					ClubsPoolBooking clubsPoolBooking = (ClubsPoolBooking) iterator2.next();

					List<ClubsPoolBookingMap> clubPoolBookingMap = clubsPoolBookingMapDAO.getClubsPoolBookingMapById(clubsPoolBooking.getId());
					if(clubPoolBookingMap == null){
						return "0";
					}

					for (Iterator<ClubsPoolBookingMap> iterator3 = clubPoolBookingMap.iterator(); iterator3.hasNext();) {
						ClubsPoolBookingMap clubsPoolBookingMap = (ClubsPoolBookingMap) iterator3.next();

						if(clubsPoolBookingMap.getServiceId() == getPoolMembershipRequestType.getServiceId()){
							int  poolBookingUsagesCount= poolBookingUsagesDAO.getPoolBookingUsagesCountById(clubsPoolBooking.getId());
							if(poolBookingUsagesCount < clubsPoolBooking.getBookedUsagesCount()){
								return String.valueOf(clubsPoolBooking.getId());
							}
						}

					}

				}
			}
		}


		return "0";
	}


}
