package com.planetclubs.dao;

import com.planetclubs.model.PoolBookingUsages;

public interface PoolBookingUsagesDAO {

	public int getPoolBookingUsagesCountById(int clubPoolBookingId);
	
	public String insertPoolBookingUsages(PoolBookingUsages poolBookingUsages);
}
