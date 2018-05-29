package com.planetclubs.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.planetclubs.dao.ServicesDAO;
import com.planetclubs.model.Services;
import com.planetclubs.model.ServicesDetails;
import com.planetclubs.util.Utils;

import in.planetclubs.ServiceDetailsListType;
import in.planetclubs.ServiceDetailsType;
import in.planetclubs.ServiceListType;
import in.planetclubs.ServiceType;

@Component
@Path("/servicesservice")
public class ServicesServiceImpl implements IServicesService{

	@Autowired
	private ServicesDAO servicesDAO;
	
	@Autowired
	Utils util;
	
	@GET
	@Path("getallservices/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ServiceListType getAllServicesByClubId(@PathParam("id") int id) {
		List<Services> lstService = servicesDAO.getAllServicesByClubId("clubId",id);

		ServiceListType serviceListType = new ServiceListType();

		for(Services service : lstService){
			ServiceType serviceType = new ServiceType();
			serviceType.setServiceId(service.getServiceId());
			serviceType.setClubId(service.getClubId());
			serviceType.setServiceType(service.getServiceType());
			serviceType.setPayAmount(service.getPayAmount());
			serviceType.setMembership(service.getMembership());
			serviceType.setWalkinAllowed(service.isWalkinAllowed());
			serviceType.setAdvancedBooking(service.isAdvancedBooking());
			serviceType.setEnrollmentsRequired(service.getEnrollmentsRequired());
			
			ServiceDetailsListType serviceDetailsListType = new ServiceDetailsListType();
			List<ServicesDetails> serviceDetailsList = servicesDAO.getAllServicesDetailsByServiceId("serviceId", service.getServiceId());
			
			
			for(ServicesDetails servicesDetails: serviceDetailsList){
				ServiceDetailsType details = new ServiceDetailsType();
				details.setId(servicesDetails.getId());
				details.setServiceDuration(servicesDetails.getServiceDuration());
				details.setEndTime(servicesDetails.getEndTime());
				details.setServiceId(servicesDetails.getServiceId());
				details.setServiceUnitCost(servicesDetails.getServiceUnitCost());
				details.setStartTime(servicesDetails.getStartTime());
				details.setWeekend(servicesDetails.isWeekend());
				serviceDetailsListType.getServiceDetailsType().add(details);
				
			}
			serviceType.setServicesDetailsListType(serviceDetailsListType);
			serviceListType.getServiceType().add(serviceType); // add to bookListType
		}
		return serviceListType;
	}
	
	@GET
	@Path("getallservicesdetails/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ServiceDetailsListType getAllServicesDetailsByServiceId(@PathParam("id") int id) {
		List<ServicesDetails> lstService = servicesDAO.getAllServicesDetailsByServiceId("serviceId",id);

		ServiceDetailsListType serviceDetailsListType = new ServiceDetailsListType();

		for(ServicesDetails serviceDetails : lstService){
			
			ServiceDetailsType serviceDetailsType = new ServiceDetailsType();
			serviceDetailsType.setServiceId(serviceDetails.getServiceId());
			serviceDetailsType.setId(serviceDetails.getId());
			serviceDetailsType.setEndTime(serviceDetails.getEndTime());
			serviceDetailsType.setServiceDuration(serviceDetails.getServiceDuration());
			serviceDetailsType.setServiceUnitCost(serviceDetails.getServiceUnitCost());
			serviceDetailsType.setStartTime(serviceDetails.getStartTime());
			serviceDetailsType.setWeekend(serviceDetails.isWeekend());
			
			serviceDetailsListType.getServiceDetailsType().add(serviceDetailsType); 
		}
		return serviceDetailsListType;
	}

	@POST
	@Path("addservice")
	@Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded"})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void contactus(@FormParam("serviceId") int serviceId, @FormParam("serviceUnitCost") int serviceUnitCost,
			@FormParam("serviceDuration") int serviceDuration, @FormParam("startTime") String startTime, 
			@FormParam("endTime") String endTime, @FormParam("weekend") boolean weekend ) {
		
		ServicesDetails servicesDetails = new ServicesDetails();
		servicesDetails.setServiceId(serviceId);
		servicesDetails.setServiceUnitCost(serviceUnitCost);
		servicesDetails.setServiceDuration(serviceDuration);
		Date start = util.setHoursAndMinutes(Integer.parseInt(startTime.substring(0,2)), Integer.parseInt(startTime.substring(3,5)));
		servicesDetails.setStartTime(start);
		Date end = util.setHoursAndMinutes(Integer.parseInt(endTime.substring(0,2)), Integer.parseInt(endTime.substring(3,5)));
		servicesDetails.setEndTime(end);
		servicesDetails.setWeekend(weekend);
		
		servicesDAO.insertNewService(servicesDetails);
		
	}

	public int getClubByServiceId(int serviceId) {
		return servicesDAO.getClubByServiceId(serviceId);
	}
	
	public ServiceListType getServiceByServiceId(int serviceId) {
		Services service = servicesDAO.getServiceByServiceId(serviceId);
		ServiceType serviceType = new ServiceType();
		serviceType.setServiceId(service.getServiceId());
		serviceType.setClubId(service.getClubId());
		serviceType.setServiceType(service.getServiceType());
		serviceType.setPayAmount(service.getPayAmount());
		serviceType.setMembership(service.getMembership());
		serviceType.setAdvancedBooking(service.isAdvancedBooking());
		serviceType.setWalkinAllowed(service.isWalkinAllowed());
		serviceType.setEnrollmentsRequired(service.getEnrollmentsRequired());
		ServiceListType serviceListType = new ServiceListType();
		serviceListType.getServiceType().add(serviceType);
		
		return serviceListType;
	}

	@Override
	public Services getServicesByServiceId(int serviceId) {
		return servicesDAO.getServiceByServiceId(serviceId);
		
	}
}
