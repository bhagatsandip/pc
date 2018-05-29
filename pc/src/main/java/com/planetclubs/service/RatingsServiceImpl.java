package com.planetclubs.service;

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

import com.planetclubs.dao.RatingsDAO;
import com.planetclubs.dao.ServicesDAO;
import com.planetclubs.model.Ratings;
import com.planetclubs.model.Services;
import com.planetclubs.model.Usage;

import in.planetclubs.RatingType;
import in.planetclubs.RatingsListType;
import in.planetclubs.ServiceListType;
import in.planetclubs.ServiceType;

@Component
@Path("/ratingsservice")
public class RatingsServiceImpl implements IRatingsService{
	
	@Autowired
	private RatingsDAO ratingsDAO;

	@GET
	@Path("getallratings/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public RatingsListType getAllRatingsByClubId(@PathParam("id")  int id) {
		List<Ratings> lstRatings = ratingsDAO.getAllRatingsByClubId("clubId",id);

		RatingsListType ratingsListType = new RatingsListType();

		for(Ratings ratings : lstRatings){
			RatingType ratingType = new RatingType();
			ratingType.setId(ratings.getId());
			ratingType.setClubId(ratings.getClubId());
			ratingType.setUserId(ratings.getUserId());
			ratingType.setRating(ratings.getRating());
			ratingType.setComment(ratings.getComment());

			
			ratingsListType.getServiceType().add(ratingType);
		}
		return ratingsListType;
	}
	

	public double getAvgRatingsByClubId(int id) {
		 return ratingsDAO.getAvgRatingsByClubId(id);
	}

	@POST
	@Path("rateclub")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public void rateClub(RatingType ratings) {

		Ratings rating = new Ratings();
		rating.setClubId(ratings.getClubId());
		rating.setUserId(ratings.getUserId());
		rating.setRating(ratings.getRating());
		rating.setComment(ratings.getComment());
		
		ratingsDAO.rateClub(rating);
		
	}

	@POST
	@Path("updaterating")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public void updateClubsRatings(RatingType ratings) {
		Ratings rating = new Ratings();
		rating.setId(ratings.getId());
		rating.setClubId(ratings.getClubId());
		rating.setUserId(ratings.getUserId());
		rating.setRating(ratings.getRating());
		rating.setComment(ratings.getComment());
		
		ratingsDAO.updateClubsRatings(rating);
		
	}

}
