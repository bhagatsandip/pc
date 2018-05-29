package com.planetclubs.dao;

import java.util.List;

import com.planetclubs.model.Usage;

public interface UsageDAO {


	String insertNewUsageInfo(Usage usage);

	Usage getUsageInfo(int usageId);

	String updateUsageInfo(Usage usage);

	List<Usage> getAllUsageInfo(String field,int id);

}
