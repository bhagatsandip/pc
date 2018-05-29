package com.planetclubs.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.planetclubs.dao.ClubsDAO;
import com.planetclubs.model.ClubPromotions;
import com.planetclubs.model.Clubs;

import in.planetclubs.ClubListType;
import in.planetclubs.ClubType;


@Component
@Path("/clubservice")
public class ClubServiceImpl implements IClubService {

	@Autowired
	private ClubsDAO clubsDAO;
	
	@Autowired
	private ServicesServiceImpl serviceDAO;
	@Autowired
	private RatingsServiceImpl ratingsDAO;

	// Basic CRUD operations for Club Service

	// http://localhost:8080/Jersey-Spring-Hibernate/rest/bookservice/addbook
	@POST
	@Path("addclub")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String createOrSaveClubInfo(ClubType clubType) {

		// unwrap bookType and set in Model object Club
		Clubs club = new Clubs();
		club.setAboutUs(clubType.getAboutUs());
		club.setClubAddress(clubType.getClubAddress());
		club.setClubLocation(clubType.getClubLocation());
		club.setClubCity(clubType.getClubCity());
		club.setClubCountry(clubType.getClubCountry());
		club.setClubId(clubType.getClubId());
		club.setClubName(clubType.getClubName());
		club.setClubType(clubType.getClubType());
		club.setContactNo1(clubType.getContactNo1());
		club.setContactNo2(clubType.getContactNo2());
		club.setEmail(clubType.getEmail());
		club.setLatitude(clubType.getLatitude());
		club.setLongitude(clubType.getLongitude());
		club.setUserId(clubType.getUserId());
		club.setMembershipType(clubType.getMembershipType());
		club.setFacilities(clubType.getFacilities());
		club.setNotes(clubType.getNotes());
		
		return clubsDAO.insertNewClubInfo(club);
	}

	// http://localhost:8080/Jersey-Spring-Hibernate/rest/bookservice/getbook/1
	@GET
	@Path("getclub/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ClubType getClubInfo(@PathParam("id") int clubId) {

		// retrieve book information based on the id supplied in the formal argument
		Clubs club = clubsDAO.getClubInfo(clubId);

		ClubType clubType = new ClubType();
		clubType.setAboutUs(club.getAboutUs());
		clubType.setClubAddress(club.getClubAddress());
		clubType.setClubLocation(club.getClubLocation());
		clubType.setClubCity(club.getClubCity());
		clubType.setClubCountry(club.getClubCountry());
		clubType.setClubId(club.getClubId());
		clubType.setClubName(club.getClubName());
		clubType.setClubType(club.getClubType());
		clubType.setContactNo1(club.getContactNo1());
		clubType.setContactNo2(club.getContactNo2());
		clubType.setEmail(club.getEmail());
		clubType.setLatitude(club.getLatitude());
		clubType.setLongitude(club.getLongitude());
		clubType.setUserId(club.getUserId());
		clubType.setMembershipType(club.getMembershipType());
		clubType.setFacilities(club.getFacilities());
		clubType.setNotes(club.getNotes());
		
		
		return clubType;
	}
	
	// http://localhost:8080/Jersey-Spring-Hibernate/rest/bookservice/getbook/1
	@GET
	@Path("getclubbyclubuserid/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ClubType getClubInfoByClubUserId(@PathParam("id") int userId) {

		// retrieve book information based on the id supplied in the formal argument
		Clubs club = clubsDAO.getClubInfoByClubUserId(userId);

		ClubType clubType = new ClubType();
		clubType.setAboutUs(club.getAboutUs());
		clubType.setClubAddress(club.getClubAddress());
		clubType.setClubLocation(club.getClubLocation());
		clubType.setClubCity(club.getClubCity());
		clubType.setClubCountry(club.getClubCountry());
		clubType.setClubId(club.getClubId());
		clubType.setClubName(club.getClubName());
		clubType.setClubType(club.getClubType());
		clubType.setContactNo1(club.getContactNo1());
		clubType.setContactNo2(club.getContactNo2());
		clubType.setEmail(club.getEmail());
		clubType.setLatitude(club.getLatitude());
		clubType.setLongitude(club.getLongitude());
		clubType.setUserId(club.getUserId());
		clubType.setMembershipType(club.getMembershipType());
		clubType.setFacilities(club.getFacilities());
		clubType.setNotes(club.getNotes());
		
		
		return clubType;
	}

	// http://localhost:8080/Jersey-Spring-Hibernate/rest/bookservice/updatebook
	@PUT
	@Path("updateclub")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateClubInfo(ClubType clubType) {

		// unwrap bookType and set in Model object Club
		Clubs club = new Clubs();
		club.setId(clubType.getId());
		club.setAboutUs(clubType.getAboutUs());
		club.setClubAddress(clubType.getClubAddress());
		club.setClubLocation(clubType.getClubLocation());
		club.setClubCity(clubType.getClubCity());
		club.setClubCountry(clubType.getClubCountry());
		club.setClubId(clubType.getClubId());
		club.setClubName(clubType.getClubName());
		club.setClubType(clubType.getClubType());
		club.setContactNo1(clubType.getContactNo1());
		club.setContactNo2(clubType.getContactNo2());
		club.setEmail(clubType.getEmail());
		club.setLatitude(clubType.getLatitude());
		club.setLongitude(clubType.getLongitude());
		club.setUserId(clubType.getUserId());
		club.setMembershipType(clubType.getMembershipType());
		club.setFacilities(clubType.getFacilities());
		club.setNotes(clubType.getNotes());

		// update book info & return SUCCESS message
		return clubsDAO.updateClubInfo(club);
	}

	// http://localhost:8080/Jersey-Spring-Hibernate/rest/bookservice/getallbook
	@GET
	@Path("getallclub")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ClubListType getAllClubInfo() {

		List<Clubs> lstClub = clubsDAO.getAllClubInfo();

		// create a object of type ClubListType which takes book objects in its list
		ClubListType clubListType = new ClubListType();

		for(Clubs club : lstClub){
			ClubType clubType = new ClubType();
			
			clubType.setId(club.getId());
			clubType.setAboutUs(club.getAboutUs());
			clubType.setClubAddress(club.getClubAddress());
			clubType.setClubLocation(club.getClubLocation());
			clubType.setClubCity(club.getClubCity());
			clubType.setClubCountry(club.getClubCountry());
			clubType.setClubId(club.getClubId());
			clubType.setClubName(club.getClubName());
			clubType.setClubType(club.getClubType());
			clubType.setContactNo1(club.getContactNo1());
			clubType.setContactNo2(club.getContactNo2());
			clubType.setEmail(club.getEmail());
			clubType.setLatitude(club.getLatitude());
			clubType.setLongitude(club.getLongitude());
			clubType.setUserId(club.getUserId());
			clubType.setMembershipType(club.getMembershipType());
			clubType.setFacilities(club.getFacilities());
			clubType.setNotes(club.getNotes());
			
			clubType.setServiceList(serviceDAO.getAllServicesByClubId(club.getUserId()));
			clubType.setRatings(ratingsDAO.getAvgRatingsByClubId(club.getId()));
			
			clubListType.getClubType().add(clubType); // add to bookListType
		}
		return clubListType;
	}

	@Override
	public ClubType getClubByServiceId(int serviceId) {
		int clubId = serviceDAO.getClubByServiceId(serviceId);
		ClubType club = getClubInfoByClubUserId(clubId);
		club.setServiceList(serviceDAO.getServiceByServiceId(serviceId));
		return club;
	}

	@Override
	public List<ClubPromotions> getClubPromotions(String location) {
		return clubsDAO.getClubPromotions(location);
	}
}