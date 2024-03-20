package com.PersonalBank.NotificationSystem.controllers;

import com.PersonalBank.NotificationSystem.entities.BankCard;
import com.PersonalBank.NotificationSystem.entities.DTO.BankCardDto;
import com.PersonalBank.NotificationSystem.services.BankCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing bank cards.
 */
@RestController
@RequestMapping("/bank-cards")
@RequiredArgsConstructor
public class BankCardController {

    private final BankCardService bankCardService;

    /**
     * Endpoint to retrieve all bank cards.
     *
     * @return ResponseEntity containing a list of bank cards if successful, or an error message if failed.
     */
    @GetMapping
    public ResponseEntity<?> getAllBankCards() {
        return ResponseEntity.ok(bankCardService.getAllBankCards());
    }

    /**
     * Endpoint to create a new bank card.
     *
     * @param bankCard  The bank card information to create.
     * @param clientId  The ID of the associated client.
     * @return ResponseEntity containing the created bank card if successful, or an error message if failed.
     */
    @PostMapping
    public ResponseEntity<?> createBankCard(@RequestBody BankCardDto bankCard, @RequestParam("id") Long clientId) {
        if (bankCardService.checkBankCardExisting(bankCard)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Card number is not unique"));
        }
        BankCard createdBankCard = bankCardService.createBankCard(bankCard, clientId);
        return new ResponseEntity<>(createdBankCard, HttpStatus.CREATED);
    }

    /**
     * Endpoint to delete a bank card.
     *
     * @param cardId    The ID of the bank card to delete.
     * @return ResponseEntity containing the deleted bank card if successful, or an error message if failed.
     */
    @DeleteMapping
    public ResponseEntity<?> deleteBankCard(@RequestParam("id") Long cardId) {
        BankCard bankCard = bankCardService.deleteBankCard(cardId);
        return ResponseEntity.ok(bankCard);
    }

    /**
     * Endpoint to change a bank card.
     *
     * @param cardId    The ID of the bank card to change.
     * @return ResponseEntity containing the new bank card if successful, or an error message if failed.
     */
    @PatchMapping
    public ResponseEntity<?> changeBankCard(@RequestParam("id") Long cardId) {
        BankCard newBankCard = bankCardService.changeBankCard(cardId);
        return new ResponseEntity<>(newBankCard, HttpStatus.CREATED);
    }
}
