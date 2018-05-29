package com.planetclubs.dao;

import java.util.List;

import com.planetclubs.model.Ratings;

public interface RatingsDAO {

	List<Ratings> getAllRatingsByClubId(String field,int id);
	
	double getAvgRatingsByClubId(int id);
	
	void rateClub(Ratings ratings);

	void updateClubsRatings(Ratings ratings);
	
}
