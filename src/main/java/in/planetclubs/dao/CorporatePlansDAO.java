package com.planetclubs.dao;

import java.util.List;

import com.planetclubs.model.CorporatePlans;

public interface CorporatePlansDAO {

	public List<CorporatePlans> getCorporatePlanById(int corporateId);
}
