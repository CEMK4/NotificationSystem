package com.PersonalBank.NotificationSystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Calendar;
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
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Client client;

    @Size(min = 16, max = 19)
    @NotBlank
    @NotEmpty
    @NotNull
    @Column(name = "card_number", unique = true)
    private String cardNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "issue_date")
    private Date issueDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiry_date")
    private Date expiryDate;

    public BankCard(Client client, String cardNumber, Date issueDate) {
        this.client = client;
        this.cardNumber = cardNumber;
        this.issueDate = issueDate;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(issueDate);
        calendar.add(Calendar.MINUTE, 1);
        Date nextDate = calendar.getTime();

        this.expiryDate = nextDate;
    }

}