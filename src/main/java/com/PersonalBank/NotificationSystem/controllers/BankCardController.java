package com.PersonalBank.NotificationSystem.controllers;

import com.PersonalBank.NotificationSystem.entities.BankCard;
import com.PersonalBank.NotificationSystem.entities.DTO.BankCardDto;
import com.PersonalBank.NotificationSystem.entities.DTO.ClientDto;
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
    public ResponseEntity<?> createBankCard(@RequestBody BankCardDto bankCard, @RequestParam("id") Long clientId) {
        if (bankCardService.checkBankCardExisting(bankCard)) {
            System.out.println("----------------------------Error 1 -------------------------");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Card number is not unique"));

        }
        BankCard createdBankCard = bankCardService.createBankCard(bankCard, clientId);
        System.out.println("----------------------------Error 2 -------------------------");
        return new ResponseEntity<>(createdBankCard, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBankCard(@PathVariable Long id) {
        bankCardService.deleteBankCard(id);
        return ResponseEntity.ok().build();
    }
}