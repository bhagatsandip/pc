package com.planetclubs.dao;

import java.util.List;

import com.planetclubs.model.Payments;

public interface PaymentDAO {

	List<Payments> getAllPayments(String string, int clubId);


}
