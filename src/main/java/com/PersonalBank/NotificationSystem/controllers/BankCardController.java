package com.PersonalBank.NotificationSystem.controllers;

import com.PersonalBank.NotificationSystem.entities.BankCard;
import com.PersonalBank.NotificationSystem.services.BankCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank-cards")
@RequiredArgsConstructor
public class BankCardController {

    private final BankCardService bankCardService;

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