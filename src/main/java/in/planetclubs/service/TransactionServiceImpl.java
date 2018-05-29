package com.planetclubs.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.planetclubs.dao.TransactionDAO;
import com.planetclubs.model.Membership;
import com.planetclubs.model.Transaction;

import in.planetclubs.MembershipType;
import in.planetclubs.TransactionType;

@Component
@Path("/transactionservice")
public class TransactionServiceImpl implements ITransactionService{

	@Autowired
	private TransactionDAO transactionDAO;
	
	@POST
	@Path("addtransaction")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String createOrSaveTransactions(TransactionType transactionType) {
		
		Transaction transaction = new Transaction();
		
		transaction.setUserId(transactionType.getUserId());
		transaction.setOperationId(transactionType.getOperationId());
		transaction.setOperationType(transactionType.getOperationType());
		transaction.setAmount(transactionType.getAmount());
		transaction.setContact(transactionType.getContact());
		transaction.setCreateDate(transactionType.getCreateDate());
		transaction.setEmail(transactionType.getEmail());
		transaction.setName(transactionType.getName());
		transaction.setStatus(transactionType.getStatus());
		transaction.setTransactionId(transactionType.getTransactionId());
		
		return transactionDAO.createOrSaveTransactions(transaction);
	}
	
	@POST
	@Path("updatetransaction")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateTransaction(TransactionType transactionType) {

		Transaction transaction = new Transaction();
		
		transaction.setId(transactionType.getId());
		transaction.setUserId(transactionType.getUserId());
		transaction.setOperationId(transactionType.getOperationId());
		transaction.setOperationType(transactionType.getOperationType());
		transaction.setAmount(transactionType.getAmount());
		transaction.setContact(transactionType.getContact());
		transaction.setCreateDate(transactionType.getCreateDate());
		transaction.setEmail(transactionType.getEmail());
		transaction.setName(transactionType.getName());
		transaction.setStatus(transactionType.getStatus());
		transaction.setTransactionId(transactionType.getTransactionId());
		
		return transactionDAO.updateTransaction(transaction);
	}
	
	

}
