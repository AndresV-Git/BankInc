package com.bankInc.credibanco.ms.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bankInc.credibanco.ms.dtos.ActivateCardDTO;
import com.bankInc.credibanco.ms.dtos.GenerateCardNumberDTO;
import com.bankInc.credibanco.ms.dtos.ReloadBalanceDTO;
import com.bankInc.credibanco.ms.entities.Card;
import com.bankInc.credibanco.ms.services.ICardService;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private ICardService cardService;

    @GetMapping("/number")
    public String generateCardNumber(@RequestParam String productId, @RequestParam String holderName) {
        return cardService.generateCardNumber(productId, holderName);
    }

    @PostMapping("/enroll")
    public ResponseEntity<Card> activateCard(@RequestBody ActivateCardDTO dto) {
        return ResponseEntity.ok(cardService.activateCard(dto.getCardId()));
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<Card> blockCard(@PathVariable String cardId) {
        return ResponseEntity.ok(cardService.blockCard(cardId));
    }

    @PostMapping("/balance")
    public ResponseEntity<Card> reloadBalance(@RequestBody ReloadBalanceDTO dto) {
        return ResponseEntity.ok(cardService.reloadBalance(dto.getCardId(), dto.getBalance()));
    }

    @GetMapping("/balance/{cardId}")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable String cardId) {
        return ResponseEntity.ok(cardService.getBalance(cardId));
    }
}