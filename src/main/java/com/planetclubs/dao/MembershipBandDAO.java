package com.planetclubs.dao;

import java.util.List;

import com.planetclubs.model.MembershipBand;

public interface MembershipBandDAO {

	public MembershipBand getMembershipBandInfo(int id);

	public List<MembershipBand> getAllMembershipBandInfo();
}