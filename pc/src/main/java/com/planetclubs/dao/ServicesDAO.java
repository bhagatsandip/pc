package com.planetclubs.dao;

import java.util.List;

import com.planetclubs.model.Clubs;
import com.planetclubs.model.Services;
import com.planetclubs.model.ServicesDetails;

public interface ServicesDAO {

	List<Services> getAllServicesByClubId(String field,int id);

	List<ServicesDetails> getAllServicesDetailsByServiceId(String string, int id);
	
	String insertNewService(ServicesDetails servicesDetails);

	int getClubByServiceId(int serviceId);

	Services getServiceByServiceId(int serviceId);
}
