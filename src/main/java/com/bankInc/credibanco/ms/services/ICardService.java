package com.bankInc.credibanco.ms.services;

import java.math.BigDecimal;

import com.bankInc.credibanco.ms.entities.Card;

public interface ICardService {

	String generateCardNumber(String productId, String holderName);

	Card activateCard(String cardId);

	Card blockCard(String cardId);

	Card reloadBalance(String cardId, BigDecimal amount);

	BigDecimal getBalance(String cardId);
	
	

}
