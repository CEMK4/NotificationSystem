package com.PersonalBank.NotificationSystem.repositories;

import com.PersonalBank.NotificationSystem.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByFullNameAndDateOfBirth(String fullName, Date dateOfBirth);
}