package com.PersonalBank.NotificationSystem.services;

import com.PersonalBank.NotificationSystem.entities.BankCard;
import com.PersonalBank.NotificationSystem.repositories.BankCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankCardService {

    private final BankCardRepository bankCardRepository;

    public List<BankCard> getAllBankCards() {
        return bankCardRepository.findAll();
    }

    public BankCard createBankCard(BankCard bankCard) {
        return bankCardRepository.save(bankCard);
    }
}