/**
 * The ClientRepository interface provides methods for accessing and managing client entities in the database.
 */
package com.PersonalBank.NotificationSystem.repositories;

import com.PersonalBank.NotificationSystem.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

/**
 * The repository interface for managing Client entities.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

    /**
     * Finds a client by their full name and date of birth.
     *
     * @param fullName The full name of the client.
     * @param dateOfBirth The date of birth of the client.
     * @return The client with the specified full name and date of birth, or null if not found.
     */
    Client findByFullNameAndDateOfBirth(String fullName, LocalDate dateOfBirth);
}
