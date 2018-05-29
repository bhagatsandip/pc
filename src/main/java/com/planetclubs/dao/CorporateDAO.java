package com.planetclubs.dao;

import java.util.List;

import com.planetclubs.model.Corporate;

public interface CorporateDAO {

	List<Corporate> getAllCorporatesInfo();

	int getCorporateById(int id);
	
	Corporate getCorporate(int id);

}
