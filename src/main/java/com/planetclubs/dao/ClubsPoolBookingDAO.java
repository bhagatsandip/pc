package com.planetclubs.dao;

import java.util.List;

import com.planetclubs.model.ClubsPoolBooking;

public interface ClubsPoolBookingDAO {

	public List<ClubsPoolBooking> getClubsPoolBookingById(int corporatePlanId);
}
