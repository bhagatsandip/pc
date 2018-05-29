package com.planetclubs.dao;

import java.util.List;

import com.planetclubs.model.ClubsPoolBookingMap;

public interface ClubsPoolBookingMapDAO {

	public List<ClubsPoolBookingMap> getClubsPoolBookingMapById(int clubsPoolBookingId);
	
	public boolean checkServiceById(int serviceId);
}
