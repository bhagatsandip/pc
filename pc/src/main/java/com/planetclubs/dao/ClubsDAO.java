package com.planetclubs.dao;

import java.util.List;

import com.planetclubs.model.ClubPromotions;
import com.planetclubs.model.Clubs;

public interface ClubsDAO {

	public String insertNewClubInfo(Clubs club);

	public Clubs getClubInfo(int clubId);
	
	public Clubs getClubInfoByClubUserId(int userId);

	public String updateClubInfo(Clubs club);

	public List<Clubs> getAllClubInfo();

	public List<ClubPromotions> getClubPromotions(String location);

}