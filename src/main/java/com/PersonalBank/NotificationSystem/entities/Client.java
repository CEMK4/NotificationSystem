/**
 * The Client class represents a client entity in the system.
 */
package com.PersonalBank.NotificationSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Entity class representing a client.
 */
@Entity
@Table(name = "clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String fullName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BankCard> bankCards = new LinkedHashSet<>();

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    /**
     * Constructs a client with the specified full name and date of birth.
     *
     * @param fullName    The full name of the client.
     * @param dateOfBirth The date of birth of the client.
     */
    public Client(String fullName, LocalDate dateOfBirth) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Adds a bank card to the client's collection of bank cards.
     *
     * @param card The bank card to add.
     */
    public void addCard(BankCard card) {
        this.bankCards.add(card);
        card.setClient(this);
    }

    /**
     * Removes a bank card from the client's collection of bank cards.
     *
     * @param card The bank card to remove.
     */
    public void removeCard(BankCard card) {
        this.bankCards.remove(card);
        card.setClient(null);
    }
}
