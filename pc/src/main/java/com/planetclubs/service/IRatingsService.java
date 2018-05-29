package com.planetclubs.service;

import in.planetclubs.RatingType;
import in.planetclubs.RatingsListType;

public interface IRatingsService {

	RatingsListType getAllRatingsByClubId(int id);
	
	void rateClub(RatingType ratings);

	void updateClubsRatings(RatingType ratings);
}
