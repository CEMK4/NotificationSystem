package com.PersonalBank.NotificationSystem.services;

import com.PersonalBank.NotificationSystem.models.BankCard;
import com.PersonalBank.NotificationSystem.repositories.BankCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankCardService {
    @Autowired
    private BankCardRepository bankCardRepository;

    public List<BankCard> getAllBankCards() {
        return bankCardRepository.findAll();
    }

    public BankCard createBankCard(BankCard bankCard) {
        return bankCardRepository.save(bankCard);
    }
}