package com.planetclubs.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.planetclubs.dao.PoolBookingUsagesDAO;
import com.planetclubs.model.PoolBookingUsages;

import in.planetclubs.PoolBookingUsagesType;

@Component
@Path("/poolbookingusageservice")
public class PoolBookingUsagesServiceImpl implements IPoolBookingUsagesService{

	@Autowired
	private PoolBookingUsagesDAO poolBookingUsagesDAO;

	// Basic CRUD operations for Book Service

	// http://localhost:8080/Jersey-Spring-Hibernate/rest/bookservice/addbook
	@POST
	@Path("addbooking")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String createOrSaveUserInfo(PoolBookingUsagesType poolBookingUsagesType) {
		
		PoolBookingUsages poolBookingUsages = new PoolBookingUsages();
		
		poolBookingUsages.setClubPoolBookingId(poolBookingUsagesType.getClubPoolBookingId());
		poolBookingUsages.setUsageId(poolBookingUsagesType.getUsageId());
		
		return poolBookingUsagesDAO.insertPoolBookingUsages(poolBookingUsages);
	}

}
