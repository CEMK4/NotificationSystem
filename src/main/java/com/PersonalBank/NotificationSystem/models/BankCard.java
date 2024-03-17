package com.PersonalBank.NotificationSystem.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bank_cards")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BankCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    private String cardNumber;

    private Date issueDate;

    private Date expiryDate;
}