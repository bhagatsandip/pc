package com.planetclubs.dao;

import com.planetclubs.model.Transaction;

public interface TransactionDAO {

	String createOrSaveTransactions(Transaction transaction);

	String updateTransaction(Transaction transaction);
}
