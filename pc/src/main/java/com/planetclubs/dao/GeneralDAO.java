package com.planetclubs.dao;

import java.util.List;

import com.planetclubs.model.Coupons;
import com.planetclubs.model.ReferenceData;

public interface GeneralDAO {

	List<ReferenceData> getReferenceData();

	Coupons checkCouponCode(String couponCode);

}
