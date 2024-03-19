package com.PersonalBank.NotificationSystem.repositories;

import com.PersonalBank.NotificationSystem.entities.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BankCardRepository extends JpaRepository<BankCard, Long> {


    @Query("select b from BankCard b where b.expiryDate <= :expiryDate")
    List<BankCard> findByExpiryDate(@Param("expiryDate") Date expiryDate);

    boolean existsByCardNumber(String cardNumber);

}