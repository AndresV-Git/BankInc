package com.bankInc.credibanco.ms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankInc.credibanco.ms.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCard_CardId(String cardId);
}

