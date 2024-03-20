/**
 * The BankCardRepository interface provides methods for accessing and managing bank card entities in the database.
 */
package com.PersonalBank.NotificationSystem.repositories;

import com.PersonalBank.NotificationSystem.entities.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The repository interface for managing BankCard entities.
 */
public interface BankCardRepository extends JpaRepository<BankCard, Long> {

    /**
     * Finds bank cards with expiry dates before or equal to the specified expiry date.
     *
     * @param expiryDate The expiry date to search for.
     * @return A list of bank cards with expiry dates before or equal to the specified expiry date.
     */
    @Query("select b from BankCard b where b.expiryDate <= :expiryDate")
    List<BankCard> findByExpiryDate(@Param("expiryDate") LocalDateTime expiryDate);

    /**
     * Checks if a bank card with the specified card number exists.
     *
     * @param cardNumber The card number to check.
     * @return True if a bank card with the specified card number exists, otherwise false.
     */
    boolean existsByCardNumber(String cardNumber);
}
