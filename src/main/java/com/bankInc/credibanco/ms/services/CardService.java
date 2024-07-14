package com.bankInc.credibanco.ms.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;

import com.bankInc.credibanco.ms.entities.Card;
import com.bankInc.credibanco.ms.exceptions.MessageNotFoundException;
import com.bankInc.credibanco.ms.repositories.CardRepository;

@Service
public class CardService implements ICardService{
	@Autowired
	private Environment messages;
		
    @Autowired
    private CardRepository cardRepository;
    
    @Override
    public String generateCardNumber(String productId, String holderName) {
        Random random = new Random();
        long randomNumber = Math.abs(random.nextLong() % 1000000000000000L);
        String formattedNumber = String.format("%015d", randomNumber);
        String cardNumber = productId + formattedNumber;
        Card card = new Card();
        card.setCardId(cardNumber);
        card.setProductName(productId.equals("123456") ? "Tarjeta de Crédito" : "Tarjeta de Débito");
        card.setHolderName(holderName);
        card.setExpirationDate(LocalDate.now().plusYears(3));
        card.setActive(false);
        card.setBlocked(false);
        card.setBalance(BigDecimal.ZERO);
        cardRepository.save(card);
        return cardNumber;
    }
    
    @Override
    public Card activateCard(String cardId) {
    	Card card = cardRepository.findById(cardId).orElseThrow(() -> new MessageNotFoundException(messages.getProperty("message.response.card.cardNotFound") + cardId));
        card.setActive(true);
        return cardRepository.save(card);
    }
    
    @Override
    public Card blockCard(String cardId) {
    	Card card = cardRepository.findById(cardId).orElseThrow(() -> new MessageNotFoundException(messages.getProperty("message.response.card.cardNotFound") + cardId));
    	card.setBlocked(true);
        return cardRepository.save(card);
    }
    
    @Override
    public Card reloadBalance(String cardId, BigDecimal amount) {
    	Card card = cardRepository.findById(cardId).orElseThrow(() -> new MessageNotFoundException(messages.getProperty("message.response.card.cardNotFound") + cardId));
        card.setBalance(card.getBalance().add(amount));
        return cardRepository.save(card);
    }
    
    @Override
    public BigDecimal getBalance(String cardId) {
    	Card card = cardRepository.findById(cardId).orElseThrow(() -> new MessageNotFoundException(messages.getProperty("message.response.card.cardNotFound") + cardId));
        return card.getBalance();
    }
   
}
