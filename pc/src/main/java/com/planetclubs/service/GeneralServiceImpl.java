package com.planetclubs.service;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.planetclubs.dao.GeneralDAO;
import com.planetclubs.model.ClubPromotions;
import com.planetclubs.model.Coupons;
import com.planetclubs.model.ReferenceData;
import com.planetclubs.util.Utils;

import in.planetclubs.ClubDataType;
import in.planetclubs.ClubPromotionsListType;
import in.planetclubs.ClubPromotionsType;
import in.planetclubs.CouponsType;
import in.planetclubs.ServiceListType;
import in.planetclubs.UsageListType;
import in.planetclubs.UsageType;
import in.planetclubs.UserDataType;
import in.planetclubs.UserListType;

@Component
@Path("/generalservice")
public class GeneralServiceImpl implements IGeneralService {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IUsageService usageService;
	
	@Autowired
	private IClubService clubService;
	
	@Autowired
	private IServicesService servicesService;
	
	@Autowired
	private IMembershipService membershipService;
	
	@Autowired
	private IPCMembershipService pcMembershipService;

	@Autowired
	private GeneralDAO generalDAO;
	
	@Autowired
	private Utils utils;

/*	@Autowired
	private CorporateDAO corporateDAO;
	
	@Autowired
	private CorporatePlansDAO corporatePlansDAO;

	@Autowired
	private ClubsPoolBookingDAO clubsPoolBookingDAO;

	@Autowired
	private ClubsPoolBookingMapDAO clubsPoolBookingMapDAO;

	@Autowired
	private PoolBookingUsagesDAO poolBookingUsagesDAO;
	// Basic CRUD operations for Book Service
*/
	// http://localhost:8080/Jersey-Spring-Hibernate/rest/bookservice/addbook
	@GET
	@Path("getuserdata/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public UserDataType getUserData(@PathParam("id") int id) {

		UserDataType userDataType = new UserDataType();
		userDataType.setUserType(userService.getUserInfo(id));
		userDataType.setUsageListType(usageService.getAllUsageByUserId(id));
		userDataType.setMembershipType(membershipService.getMembershipInfo(id));
		userDataType.setPcMembershipType(pcMembershipService.getActivePCMembership(id));
		
		
/*		if(userDataType.getUserType().getCorporateId()!=0){
			int corporateId = userDataType.getUserType().getCorporateId();
			Corporate corporate = corporateDAO.getCorporate(corporateId);
			
			CorporateType corporateType = new CorporateType();
			corporateType.setAddress(corporate.getAddress());
			corporateType.setContactEmail(corporate.getContactEmail());
			corporateType.setContactNo(corporate.getContactNo());
			corporateType.setEmailDomain(corporate.getEmailDomain());
			corporateType.setDoubleVerification(corporate.isDoubleVerification());
			corporateType.setId(corporate.getId());
			corporateType.setName(corporate.getName());
			
			userDataType.getCorporatePoolDataType().setCorporateType(corporateType);
			
			List<CorporatePlans> corporatePlansList = corporatePlansDAO.getCorporatePlanById(corporateId);

			for (Iterator<CorporatePlans> iterator = corporatePlansList.iterator(); iterator.hasNext();) {
				CorporatePlans corporatePlans = (CorporatePlans) iterator.next();

				List<ClubsPoolBooking> clubPoolBookingList = clubsPoolBookingDAO.getClubsPoolBookingById(corporatePlans.getId());

				for (Iterator<ClubsPoolBooking> iterator2 = clubPoolBookingList.iterator(); iterator2.hasNext();) {
					ClubsPoolBooking clubsPoolBooking = (ClubsPoolBooking) iterator2.next();

					List<ClubsPoolBookingMap> clubPoolBookingMap = clubsPoolBookingMapDAO.getClubsPoolBookingMapById(clubsPoolBooking.getId());

					for (Iterator<ClubsPoolBookingMap> iterator3 = clubPoolBookingMap.iterator(); iterator3.hasNext();) {
						ClubsPoolBookingMap clubsPoolBookingMap = (ClubsPoolBookingMap) iterator3.next();
							int serviceId = clubsPoolBookingMap.getServiceId();
							userDataType.getCorporatePoolDataType().getClubListType().getClubType().add(clubService.getClubByServiceId(serviceId));
							
						}

					}

				}
			}*/
			
			
		
		return userDataType;
	}
	
	@GET
	@Path("getclubdata/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ClubDataType getClubData(@PathParam("id") int id) {

		ClubDataType clubDataType = new ClubDataType();
		clubDataType.setClubType(clubService.getClubInfoByClubUserId(id));
		
		UsageListType usageListType = usageService.getAllUsageByClubId(id);
		clubDataType.setUsageListType(usageListType);
		
		ServiceListType serviceListType = servicesService.getAllServicesByClubId(id);
		clubDataType.setServicesListType(serviceListType);
		
		List<UsageType> usageList = usageListType.getUsageType();
		Set<Integer> usersList = new HashSet<Integer>();
		UserListType userList = new UserListType();
		for (Iterator<UsageType> iterator = usageList.iterator(); iterator.hasNext();) {
			UsageType usageType = iterator.next();
			usersList.add(usageType.getUserId());
			//userList.getUserType().add((userService.getUserInfo(usageType.getUserId())));			
		}
		
		for (Iterator<Integer> iterator = usersList.iterator(); iterator.hasNext();) {
			Integer userId = iterator.next();
			userList.getUserType().add(userService.getUserInfo(userId));
		}
		clubDataType.setUserListType(userList);
		return clubDataType;
	}
	
	@GET
	@Path("getclubpromotions/{location}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ClubPromotionsListType getClubPromotions(@PathParam("location") String location) {

		List<ClubPromotions> promotionsLst = clubService.getClubPromotions(location);
		ClubPromotionsListType clubPromotionsListType = new ClubPromotionsListType();
		for (Iterator<ClubPromotions> iterator = promotionsLst.iterator(); iterator.hasNext();) {
			ClubPromotions clubPromotions = iterator.next();
			ClubPromotionsType promotionsType= new ClubPromotionsType();
			
			promotionsType.setId(clubPromotions.getId());
			promotionsType.setBanner(clubPromotions.getBanner());
			promotionsType.setClubId(clubPromotions.getClubId());
			promotionsType.setFromDate(clubPromotions.getFromDate());
			promotionsType.setToDate(clubPromotions.getToDate());
			promotionsType.setLocation(clubPromotions.getLocation());
			promotionsType.setLive(clubPromotions.isLive());
			clubPromotionsListType.getClubPromotionsType().add(promotionsType);
		}
		
		return clubPromotionsListType;
		
		
	}
	
	@GET
	@Path("getcurrentdate")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Date getCurrentDateTime() {

		return utils.getCurrentTime();
	}
	
	@GET
	@Path("getreferencedata")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Map<String,String> getReferenceData() {

		Map<String,String> map= new HashMap<>();
		
		List<ReferenceData> referenceDataList = generalDAO.getReferenceData();
		for (Iterator<ReferenceData> iterator = referenceDataList.iterator(); iterator.hasNext();) {
			 ReferenceData refData = iterator.next();
			 map.put(refData.getFieldKey(), refData.getFieldValue());
		}
		
		return map;
	}
	
	@GET
	@Path("checkCouponCode/{couponCode}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public CouponsType checkCouponCode(@PathParam("couponCode") String couponCode) {

		
		Coupons coupon = generalDAO.checkCouponCode(couponCode);
		
		Date now = utils.getCurrentTime();
		
		if(coupon!=null){
			if((coupon.getValidFrom().compareTo(now) * now.compareTo(coupon.getValidTo()) >= 0) && coupon.isLive()){
				CouponsType couponType = new CouponsType();
				
				couponType.setCouponCode(coupon.getCouponCode());
				couponType.setCouponDesc(coupon.getCouponDesc());
				couponType.setDiscount(coupon.getDiscount());
				couponType.setDiscType(coupon.getDiscType());
				couponType.setFupBonus(coupon.getFupBonus());
				couponType.setId(coupon.getId());
				couponType.setLive(coupon.isLive());
				couponType.setValidFrom(coupon.getValidFrom());
				couponType.setValidTo(coupon.getValidTo());
				return couponType;
			}
		}
		return null;
	}

}