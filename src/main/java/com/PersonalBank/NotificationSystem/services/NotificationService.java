package com.PersonalBank.NotificationSystem.services;

import com.PersonalBank.NotificationSystem.repositories.BankCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final BankCardRepository bankCardRepository;
    public void CheckExpirationDateOfCard(){
        Calendar calendar = Calendar.getInstance();
        Date currentDateAndTime = calendar.getTime();
        var cards = bankCardRepository.findByExpiryDate(currentDateAndTime);
        System.out.println(cards);
    }
}
