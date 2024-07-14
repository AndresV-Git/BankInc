package com.bankInc.credibanco.ms.services;

import java.math.BigDecimal;

import com.bankInc.credibanco.ms.dtos.TransactionDTO;

public interface ITransactionService {

	TransactionDTO purchase(String cardId, BigDecimal amount);

	TransactionDTO cancelTransaction(String cardId, Long transactionId);

	TransactionDTO getTransaction(Long transactionId);

}
