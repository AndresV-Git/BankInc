package com.bankInc.credibanco.ms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankInc.credibanco.ms.entities.Card;

public interface CardRepository extends JpaRepository<Card, String> {
	
}
