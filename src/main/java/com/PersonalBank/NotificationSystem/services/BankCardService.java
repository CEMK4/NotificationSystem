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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BankCardService {

    private final BankCardRepository bankCardRepository;
    private final ClientRepository clientRepository;

    public List<BankCard> getAllBankCards() {
        return bankCardRepository.findAll();
    }

    @Transactional
    public BankCard createBankCard(BankCardDto bankCard, Long clientId) {
        var client = clientRepository.getById(clientId);
        var bankCardData = new BankCard(client, bankCard.getCardNumber(), bankCard.getIssueDate());
        bankCardRepository.save(bankCardData);
        var cards = client.getBankCards();
        cards.add(bankCardData);
        client.setBankCards(cards);
        clientRepository.save(client);
        return null;
    }

    public boolean checkBankCardExisting(BankCardDto bankCardDto){ return bankCardRepository.existsByCardNumber(bankCardDto.getCardNumber());}

    public void deleteBankCard(Long id) {
        bankCardRepository.deleteById(id);
    }

    public void init() {
        Calendar calendar = Calendar.getInstance();
        Date currentDateAndTime = calendar.getTime();
        var bankCard = new BankCard(clientRepository.getById(1L),"1000 2000 3000 4000", currentDateAndTime);
        bankCardRepository.save(bankCard);
    }
}