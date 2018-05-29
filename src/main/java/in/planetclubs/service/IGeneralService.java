package com.planetclubs.service;


import java.util.Date;
import java.util.Map;

import in.planetclubs.ClubDataType;
import in.planetclubs.ClubPromotionsListType;
import in.planetclubs.CouponsType;
import in.planetclubs.UserDataType;

public interface IGeneralService {

	public UserDataType getUserData(int id);
	public ClubPromotionsListType getClubPromotions(String location);
	public ClubDataType getClubData(int id);
	public Map<String,String> getReferenceData();
	public Date getCurrentDateTime();
	public CouponsType checkCouponCode(String couponCode);

	
}
