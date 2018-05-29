package com.planetclubs.model;

import java.util.List;

public class ClubData {

	private List<Usage> usageList;
	
	private Clubs club;
	
	private List<Users> usersList;
	
	private List<Services> servicesList;
	
	public List<Usage> getUsageList() {
		return usageList;
	}

	public void setUsageList(List<Usage> usageList) {
		this.usageList = usageList;
	}

	public Clubs getClub() {
		return club;
	}

	public void setClub(Clubs club) {
		this.club = club;
	}

	public List<Users> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<Users> usersList) {
		this.usersList = usersList;
	}

	public List<Services> getServicesList() {
		return servicesList;
	}

	public void setServicesList(List<Services> servicesList) {
		this.servicesList = servicesList;
	}		
}

