package com.planetclubs.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.planetclubs.dao.UsageDAO;
import com.planetclubs.model.Usage;
import com.planetclubs.util.PlanetClubError;
import com.planetclubs.util.Utils;
import com.planetclubs.util.ValidateUtil;

import in.planetclubs.UsageListType;
import in.planetclubs.UsageType;

@Component
@Path("/usageservice")
public class UsageServiceImpl implements IUsageService {

	@Autowired
	private UsageDAO usageDAO;
	
	@Autowired
	private ValidateUtil validateUtil;
	
	@Autowired
	private Utils utils;
	

	@POST
	@Path("addusage")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String createOrSaveUsageInfo(UsageType usageType) {

		Date now = utils.getCurrentTime();
		
		long secs = (usageType.getEndDateTime().getTime() - usageType.getStartDateTime().getTime()) / 1000;
		int hours = (int) (secs / 3600);
		Date endDate = utils.addHours(hours);
		usageType.setStartDateTime(now);
		usageType.setEndDateTime(endDate);

		List<PlanetClubError> errors = validateUtil.validateUsage(usageType);
		if(errors.size() > 0){
			PlanetClubError error = errors.get(0);
			
			if(error.getMessage().equalsIgnoreCase("ActiveMembership")){
				usageType.setIsApproved("approved");
				usageType.setServiceUnitCost(0);
			}else if(error.getMessage().equalsIgnoreCase("ActiveSession")){
				return "ActiveSession";
			}else if(error.getMessage().equalsIgnoreCase("NotEnoughPoints")){
				return "NotEnoughPoints";
			}
		}	
		Usage usage = new Usage();
		usage.setClubId(usageType.getClubId());
		usage.setDetails(usageType.getDetails());
		usage.setEndDateTime(endDate);
		usage.setStartDateTime(now);
		usage.setIsApproved(usageType.getIsApproved());
		usage.setServiceType(usageType.getServiceType());
		usage.setPcMembershipId(usageType.getPcMembershipId());
		usage.setUserId(usageType.getUserId());
		usage.setServiceUnitCost(usageType.getServiceUnitCost());
		
		return usageDAO.insertNewUsageInfo(usage);
	}

	@GET
	@Path("getusage/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public UsageType getUsageInfo(@PathParam("id") int usageId) {

		Usage usage = usageDAO.getUsageInfo(usageId);
		
		UsageType usageType = new UsageType();
		usageType.setClubId(usage.getClubId());
		usageType.setDetails(usage.getDetails());
		usageType.setPcMembershipId(usage.getPcMembershipId());
		usageType.setEndDateTime(usage.getEndDateTime());
		usageType.setIsApproved(usage.getIsApproved());
		usageType.setServiceType(usage.getServiceType());
		usageType.setStartDateTime(usage.getStartDateTime());
		usageType.setUserId(usage.getUserId());
		usageType.setServiceUnitCost(usage.getServiceUnitCost());
		
		return usageType;
	}

	@POST
	@Path("updateusage")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateUsageInfo(UsageType usageType) {

		Usage usage = new Usage();
		usage.setId(usageType.getId());
		usage.setClubId(usageType.getClubId());
		usage.setDetails(usageType.getDetails());
		usage.setPcMembershipId(usageType.getPcMembershipId());
		usage.setEndDateTime(usageType.getEndDateTime());
		usage.setIsApproved(usageType.getIsApproved());
		usage.setServiceType(usageType.getServiceType());
		usage.setStartDateTime(usageType.getStartDateTime());
		usage.setUserId(usageType.getUserId());
		usage.setServiceUnitCost(usageType.getServiceUnitCost());
		
		return usageDAO.updateUsageInfo(usage);
	}

	
	public UsageListType getAllUsageByUserId(int userId) {

		List<Usage> lstUsage = usageDAO.getAllUsageInfo("userId",userId);

		UsageListType usageListType = new UsageListType();

		for(Usage usage : lstUsage){
			UsageType usageType = new UsageType();
			usageType.setId(usage.getId());
			usageType.setClubId(usage.getClubId());
			usageType.setDetails(usage.getDetails());
			usageType.setPcMembershipId(usage.getPcMembershipId());
			usageType.setEndDateTime(usage.getEndDateTime());
			usageType.setIsApproved(usage.getIsApproved());
			usageType.setServiceType(usage.getServiceType());
			usageType.setStartDateTime(usage.getStartDateTime());
			usageType.setUserId(usage.getUserId());
			usageType.setServiceUnitCost(usage.getServiceUnitCost());
			
			usageListType.getUsageType().add(usageType); 
		}
		return usageListType;
	}
	
	public UsageListType getAllUsageByClubId(int clubId) {

		List<Usage> lstUsage = usageDAO.getAllUsageInfo("clubId",clubId);

		UsageListType usageListType = new UsageListType();

		for(Usage usage : lstUsage){
			UsageType usageType = new UsageType();
			usageType.setId(usage.getId());
			usageType.setClubId(usage.getClubId());
			usageType.setDetails(usage.getDetails());
			usageType.setEndDateTime(usage.getEndDateTime());
			usageType.setIsApproved(usage.getIsApproved());
			usageType.setServiceType(usage.getServiceType());
			usageType.setStartDateTime(usage.getStartDateTime());
			usageType.setUserId(usage.getUserId());
			usageType.setServiceUnitCost(usage.getServiceUnitCost());
			usageType.setPcMembershipId(usage.getPcMembershipId());
			usageListType.getUsageType().add(usageType); 
		}
		return usageListType;
	}
}