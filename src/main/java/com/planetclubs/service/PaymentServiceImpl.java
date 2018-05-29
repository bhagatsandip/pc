package com.planetclubs.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.planetclubs.dao.PaymentDAO;
import com.planetclubs.dao.UserDAO;
import com.planetclubs.model.Login;
import com.planetclubs.model.Payments;
import com.planetclubs.model.Usage;
import com.planetclubs.model.Users;

import in.planetclubs.LoginType;
import in.planetclubs.PaymentListType;
import in.planetclubs.PaymentType;
import in.planetclubs.UsageListType;
import in.planetclubs.UsageType;
import in.planetclubs.UserType;

@Component
@Path("/paymentservice")
public class PaymentServiceImpl implements IPaymentService {

	@Autowired
	private PaymentDAO paymentDAO;

	@GET
	@Path("getpayments/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public PaymentListType getAllPayments(@PathParam("id") int clubId) {
		List<Payments> lstPayments = paymentDAO.getAllPayments("clubId",clubId);

		PaymentListType paymentList = new PaymentListType();

		for(Payments payment : lstPayments){
			PaymentType paymentType = new PaymentType();
			paymentType.setId(payment.getId());
			paymentType.setClubId(payment.getClubId());
			paymentType.setAmount(payment.getAmount());
			paymentType.setPaymentDate(payment.getPaymentDate());
			
			paymentList.getPaymentType().add(paymentType); 
		}
		return paymentList;
	}
}