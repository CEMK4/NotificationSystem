/**
 * The NotificationService class provides methods for managing notifications related to bank cards.
 */
package com.PersonalBank.NotificationSystem.services;

import com.PersonalBank.NotificationSystem.repositories.BankCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Service class for managing notifications related to bank cards.
 */
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final BankCardRepository bankCardRepository;
    private final BankCardService bankCardService;

    /**
     * Checks the expiration date of bank cards and generates notifications.
     * If a bank card has expired, it changes the card number and creates a notification file.
     */
    public void CheckExpirationDateOfCard() {
        var text = new StringBuilder();

        var cards = bankCardRepository.findByExpiryDate(LocalDateTime.now());
        text.append("Номера карт, с истёкшим сроком действия:");
        for (var card : cards) {
            text.append("\nИмя клиента: ").append(card.getClient().getFullName());
            text.append("\nСтарый номер карты - ").append(card.getCardNumber());
            var newCard = bankCardService.changeBankCard(card.getId());
            text.append("\nНовый номер карты - ").append(newCard.getCardNumber());
            text.append("\n");
        }

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedDateTime = dateTime.format(formatter);

        String directoryName = "logs";
        var stringBuilder = new StringBuilder();
        stringBuilder.append("logs/notifications_").append(formattedDateTime).append(".txt");
        String fileName = stringBuilder.toString();

        try {
            File file = new File(fileName);
            file.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(text.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
