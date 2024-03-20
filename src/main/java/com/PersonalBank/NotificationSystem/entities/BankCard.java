/**
 * The BankCard class represents a bank card entity in the system.
 */
package com.PersonalBank.NotificationSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Entity class representing a bank card.
 */
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

    @JsonIgnore
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
    private LocalDateTime issueDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    /**
     * Constructs a bank card with the specified card number and issue date.
     *
     * @param cardNumber The card number of the bank card.
     * @param issueDate  The issue date of the bank card.
     */
    public BankCard(String cardNumber, LocalDateTime issueDate) {
        Random random = new Random();
        this.cardNumber = cardNumber;
        this.issueDate = issueDate;
        this.expiryDate = issueDate.plusSeconds(random.nextInt(10, 30));
    }

}
