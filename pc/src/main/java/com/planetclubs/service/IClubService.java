package com.planetclubs.service;

import java.util.List;

import com.planetclubs.model.ClubPromotions;

import in.planetclubs.ClubListType;
import in.planetclubs.ClubType;

public interface IClubService {

	// Basic CRUD operations for Club Service

	public String createOrSaveClubInfo(ClubType bookType);
	public ClubType getClubInfo(int clubId);
	public ClubType getClubInfoByClubUserId(int clubId);
	public String updateClubInfo(ClubType clubType);
	public ClubListType getAllClubInfo();
	public ClubType getClubByServiceId(int serviceId);
	public List<ClubPromotions> getClubPromotions(String location);

}