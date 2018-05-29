package com.planetclubs.service;

import in.planetclubs.UsageListType;
import in.planetclubs.UsageType;

public interface IUsageService {

	public String createOrSaveUsageInfo(UsageType usage);
	public UsageType getUsageInfo(int id);
	public String updateUsageInfo(UsageType usage);
	public UsageListType getAllUsageByUserId(int id);
	public UsageListType getAllUsageByClubId(int id);
	
}
