/**
 * The BankCardService class provides methods for managing bank cards.
 */
package com.PersonalBank.NotificationSystem.services;

import com.PersonalBank.NotificationSystem.entities.BankCard;
import com.PersonalBank.NotificationSystem.entities.DTO.BankCardDto;
import com.PersonalBank.NotificationSystem.entities.DTO.ClientDto;
import com.PersonalBank.NotificationSystem.repositories.BankCardRepository;
import com.PersonalBank.NotificationSystem.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * Service class for managing bank cards.
 */
@Service
@RequiredArgsConstructor
public class BankCardService {

    private final BankCardRepository bankCardRepository;
    private final ClientRepository clientRepository;

    /**
     * Retrieves all bank cards from the database.
     *
     * @return List of BankCard objects.
     */
    public List<BankCard> getAllBankCards() {
        return bankCardRepository.findAll();
    }

    /**
     * Creates a new bank card for a given client.
     *
     * @param bankCardDto The bank card DTO object containing card details.
     * @param clientId    The ID of the client associated with the bank card.
     * @return The created BankCard object.
     */
    public BankCard createBankCard(BankCardDto bankCardDto, Long clientId) {
        var client = clientRepository.findById(clientId).get();
        var bankCard = new BankCard(bankCardDto.getCardNumber(), bankCardDto.getIssueDate());
        var newBankCard = bankCardRepository.save(bankCard);
        client.addCard(bankCard);
        clientRepository.save(client);
        return newBankCard;
    }

    /**
     * Checks if a bank card with the given card number already exists.
     *
     * @param bankCardDto The bank card DTO object containing card details.
     * @return true if the bank card already exists, false otherwise.
     */
    public boolean checkBankCardExisting(BankCardDto bankCardDto) {
        return bankCardRepository.existsByCardNumber(bankCardDto.getCardNumber());
    }

    /**
     * Deletes a bank card by its ID.
     *
     * @param id The ID of the bank card to be deleted.
     * @return The deleted BankCard object.
     */
    @Transactional
    public BankCard deleteBankCard(Long id) {
        var bankCard = bankCardRepository.findById(id).get();
        bankCardRepository.delete(bankCard);
        var client = bankCard.getClient();
        client.removeCard(bankCard);
        clientRepository.save(client);
        return bankCard;
    }

    /**
     * Initializes the bank card service by creating sample bank cards for a client.
     */
    public void init() {
        var client = clientRepository.findById(1L).get();
        var bankCard1 = new BankCard("1000 2000 3000 4000", LocalDateTime.now());
        var bankCard2 = new BankCard("5000 6000 7000 8000", LocalDateTime.now().plusSeconds(10));
        var newBankCard1 = bankCardRepository.save(bankCard1);
        var newBankCard2 = bankCardRepository.save(bankCard2);
        client.addCard(newBankCard1);
        client.addCard(newBankCard2);
        clientRepository.save(client);
    }

    /**
     * Changes the bank card number and issue date for a given card.
     *
     * @param cardId The ID of the bank card to be changed.
     * @return The updated BankCard object.
     */
    public BankCard changeBankCard(Long cardId) {
        Random random = new Random();

        var newBankCard = bankCardRepository.findById(cardId).get();
        var newBankCardNumber = randomBankCardNumber();
        while (bankCardRepository.existsByCardNumber(newBankCardNumber))
            newBankCardNumber = randomBankCardNumber();
        newBankCard.setCardNumber(newBankCardNumber);
        var localDateTime = LocalDateTime.now();
        newBankCard.setIssueDate(localDateTime);
        newBankCard.setExpiryDate(localDateTime.plusSeconds(random.nextInt(10,30)));
        return bankCardRepository.save(newBankCard);
    }

    /**
     * Generates a random bank card number.
     *
     * @return A random bank card number.
     */
    private String randomBankCardNumber() {
        String regex = "d d d d";

        Random random = new Random();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < regex.length(); i++) {
            char c = regex.charAt(i);
            if (c == 'd') {
                sb.append(random.nextInt(1000,10000));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
