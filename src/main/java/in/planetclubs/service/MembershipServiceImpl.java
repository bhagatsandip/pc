package com.planetclubs.service;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.planetclubs.dao.CorporateDAO;
import com.planetclubs.dao.MembershipDAO;
import com.planetclubs.dao.TransactionDAO;
import com.planetclubs.dao.UserDAO;
import com.planetclubs.model.Corporate;
import com.planetclubs.model.Membership;
import com.planetclubs.model.Transaction;
import com.planetclubs.model.Users;
import com.planetclubs.util.SendEmail;
import com.planetclubs.util.Utils;

import in.planetclubs.MembershipTransanctionType;
import in.planetclubs.MembershipType;
import in.planetclubs.TransactionType;

@Component
@Path("/membershipservice")
public class MembershipServiceImpl implements IMembershipService {

	@Autowired
	private MembershipDAO membershipDAO;
	
	@Autowired
	private TransactionDAO transactionDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private CorporateDAO corporateDAO;
	
	@Autowired
	Utils util;


	@POST
	@Path("addmembership")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String createOrSaveMembershipInfo(MembershipType membershipType) {

		Membership membership = new Membership();
		membership.setMembershipNo(membershipType.getMembershipNo());
		membership.setTotalAddonPoints(membershipType.getTotalAddonPoints());

		membership.setStartDate(membershipType.getStartDate());
		membership.setEndDate(membershipType.getEndDate());
		membership.setUpdateDate(membershipType.getUpdateDate());
		membership.setUserId(membershipType.getUserId());
		membership.setMembershipType(membershipType.getMembershipType());
		membership.setActive(membershipType.isActive());
		membership.setChunks(membershipType.getChunks());
		membership.setCorporateId(membershipType.getCorporateId());
		membership.setCorporateAmount(membershipType.getCorporateAmount());
		membership.setCorporateEmail(membershipType.getCorporateEmail());
		membership.setCouponCode(membershipType.getCouponCode());
		membership.setMembershipAmount(membershipType.getMembershipAmount());
		
		return membershipDAO.insertNewMembershipInfo(membership);
	}

	@GET
	@Path("getmembership/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public MembershipType getMembershipInfo(@PathParam("id") int membershipId) {

		Membership membership = membershipDAO.getMembershipInfo(membershipId);
		if(null != membership){
		
	
			MembershipType membershipType = new MembershipType();
			membershipType.setMembershipId(membership.getMembershipId());
			membershipType.setMembershipNo(membership.getMembershipNo());
			membershipType.setTotalAddonPoints(membership.getTotalAddonPoints());
			membershipType.setStartDate(membership.getStartDate());
			membershipType.setEndDate(membership.getEndDate());
			membershipType.setUpdateDate(membership.getUpdateDate());
			membershipType.setUserId(membership.getUserId());
			membershipType.setMembershipType(membership.getMembershipType());
			membershipType.setActive(membership.isActive());
			membershipType.setChunks(membership.getChunks());
			membershipType.setCorporateId(membership.getCorporateId());
			membershipType.setCorporateAmount(membership.getCorporateAmount());
			membershipType.setCorporateEmail(membership.getCorporateEmail());
			membershipType.setCouponCode(membership.getCouponCode());
			membershipType.setMembershipAmount(membership.getMembershipAmount());
		
			return membershipType;
		}
		return null;
	}

	@POST
	@Path("updatemembership")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateMembershipInfo(MembershipType membershipType) {
		Date start = util.getCurrentTime();
		Membership membership = new Membership();
		membership.setMembershipId(membershipType.getMembershipId());
		membership.setMembershipNo(membershipType.getMembershipNo());
		membership.setTotalAddonPoints(membershipType.getTotalAddonPoints());
		membership.setStartDate(membershipType.getStartDate());
		membership.setEndDate(membershipType.getEndDate());
		membership.setUpdateDate(start);
		membership.setUserId(membershipType.getUserId());
		membership.setMembershipType(membershipType.getMembershipType());
		membership.setActive(membershipType.isActive());
		membership.setChunks(membershipType.getChunks());
		membership.setCorporateId(membershipType.getCorporateId());
		membership.setCorporateAmount(membershipType.getCorporateAmount());
		membership.setCorporateEmail(membershipType.getCorporateEmail());
		membership.setCouponCode(membershipType.getCouponCode());
		membership.setMembershipAmount(membershipType.getMembershipAmount());
		
		return membershipDAO.updateMembershipInfo(membership);
	}

	
	
	@POST
	@Path("createMembershipTran")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public MembershipType createOrSaveMembershipTypeInfo(MembershipTransanctionType membershipTranType) {
		Date start = util.getCurrentTime();
		membershipTranType.getOldMembershipType().setUpdateDate(start);
		updateMembershipInfo(membershipTranType.getOldMembershipType());
		membershipTranType.getNewMembershipType().setStartDate(start);
		membershipTranType.getNewMembershipType().setUpdateDate(start);
		if(membershipTranType.getOperationType().equalsIgnoreCase("RENEW"))	{
			Date end = util.addYears(1);
			membershipTranType.getNewMembershipType().setEndDate(end);
		}
		String id = createOrSaveMembershipInfo(membershipTranType.getNewMembershipType());
		
		TransactionType transactionType = membershipTranType.getTransactionType();
		if(transactionType.getId()!=0){
			transactionType.setOperationId(Integer.parseInt(id));
			Transaction transaction = new Transaction();
			
			transaction.setId(transactionType.getId());
			transaction.setUserId(transactionType.getUserId());
			transaction.setOperationId(transactionType.getOperationId());
			transaction.setOperationType(transactionType.getOperationType());
			transaction.setAmount(transactionType.getAmount());
			transaction.setContact(transactionType.getContact());
			transaction.setCreateDate(start);
			transaction.setEmail(transactionType.getEmail());
			transaction.setName(transactionType.getName());
			transaction.setStatus(transactionType.getStatus());
			transaction.setTransactionId(transactionType.getTransactionId());
			
			
			transactionDAO.updateTransaction(transaction);
		}		
		
		Users users = userDAO.getUserInfo(membershipTranType.getNewMembershipType().getUserId());
		SendEmail emailSender = new SendEmail();
		//Corporate corporate = null;
		/*int corporateId = membershipTranType.getNewMembershipType().getCorporateId();
		if(corporateId>0){
		corporate = corporateDAO.getCorporate(corporateId);
		}*/
		emailSender.sendMembershipInvoice(users,membershipTranType.getNewMembershipType());//, corporate);
		return getMembershipInfo(membershipTranType.getNewMembershipType().getUserId());

		
	}

}