package com.PersonalBank.NotificationSystem.controllers;

import com.PersonalBank.NotificationSystem.services.BankCardService;
import com.PersonalBank.NotificationSystem.models.BankCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank-cards")
public class BankCardController {

    @Autowired
    private BankCardService bankCardService;

    @GetMapping
    public ResponseEntity<?> getAllBankCards() {
        return ResponseEntity.ok(bankCardService.getAllBankCards());
    }

    @PostMapping
    public ResponseEntity<?> createBankCard(@RequestBody BankCard bankCard) {
        BankCard createdBankCard = bankCardService.createBankCard(bankCard);
        return new ResponseEntity<>(createdBankCard, HttpStatus.CREATED);
    }
}