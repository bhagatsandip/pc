package com.planetclubs.service;

import in.planetclubs.TransactionType;

public interface ITransactionService {
	
	public String createOrSaveTransactions(TransactionType transactionType);
	public String updateTransaction(TransactionType transactionType);

}
