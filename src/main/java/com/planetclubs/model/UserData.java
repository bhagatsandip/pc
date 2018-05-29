package com.planetclubs.model;

import java.util.List;

public class UserData {
	
	private List<Usage> usageList;
	
	private Membership membership;
	
	private Users user;

	public List<Usage> getUsageList() {
		return usageList;
	}

	public void setUsageList(List<Usage> usageList) {
		this.usageList = usageList;
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	

}
