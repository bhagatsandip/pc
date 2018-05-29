package com.planetclubs.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.planetclubs.dao.UsageDAO;
import com.planetclubs.model.Membership;
import com.planetclubs.model.Services;
import com.planetclubs.model.Usage;
import com.planetclubs.service.IMembershipService;
import com.planetclubs.service.IPCMembershipService;
import com.planetclubs.service.IServicesService;

import in.planetclubs.MembershipType;
import in.planetclubs.PCMembershipType;
import in.planetclubs.UsageType;

@Component
public class ValidateUtil {
	
	
	@Autowired
	private UsageDAO usageDAO;
	
	@Autowired
	private IPCMembershipService pcMembershipService;
	
	@Autowired
	private IMembershipService membershipService;
	
	@Autowired
	private IServicesService servicesService;

	public List<PlanetClubError> validateUsage(UsageType usageType) {
		
		List<PlanetClubError> error = new ArrayList<PlanetClubError>();
		
		List<Usage> usageList = usageDAO.getAllUsageInfo("userId", usageType.getUserId());
		MembershipType membership =membershipService.getMembershipInfo(usageType.getUserId());
		
		if(!isActiveSession(usageList,usageType)){
			if(checkActiveMembeship(usageType)){
				error.add(new PlanetClubError("Activemembership"));
				return error;
			}else{
				if(usageType.getServiceUnitCost() > membership.getTotalAddonPoints()){
					error.add(new PlanetClubError("NotEnoughPoints"));
					return error;
				}
				return error;
			}
		}
		error.add(new PlanetClubError("ActiveSession"));
		return error;
	}

	private boolean checkActiveMembeship(UsageType usageType) {
		PCMembershipType pcMembership = pcMembershipService.getActivePCMembership(usageType.getUserId());
		if(pcMembership == null)
			return false;
		Services service = servicesService.getServicesByServiceId(pcMembership.getServiceId());
		if(service == null ){
			return false;
		}
		Date start = pcMembership.getStartTime();
		Date end = pcMembership.getEndTime();
		Date now = new Date();
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(now);
		startCal.setTimeZone(TimeZone.getTimeZone("IST"));
		
		now = startCal.getTime();
		
		if((start.compareTo(now) * now.compareTo(end) > 0) &&
				pcMembership.getUserId() == usageType.getUserId() &&
				pcMembership.getClubId() == usageType.getClubId() &&
				service.getServiceType().equalsIgnoreCase(usageType.getServiceType())){
			return true;
		}
		return false;
	}

	private boolean isActiveSession(List<Usage> usageList, UsageType usageType) {
		
		
		Date now = new Date();
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(now);
		startCal.setTimeZone(TimeZone.getTimeZone("IST"));
		
		now = startCal.getTime();
		
		for (Iterator<Usage> iterator = usageList.iterator(); iterator.hasNext();) {
			Usage usage = iterator.next();
			Date start = usage.getStartDateTime();
			Date end = usage.getEndDateTime();
			if((start.compareTo(now) * now.compareTo(end) > 0) &&
					usageType.getServiceType().equalsIgnoreCase(usage.getServiceType()) &&
					usageType.getUserId()==usage.getUserId() &&
					usageType.getClubId() == usage.getClubId() &&
					!usage.getIsApproved().equalsIgnoreCase("rejected")){
				return true;
			}
			
		}
		return false;
	}

}
