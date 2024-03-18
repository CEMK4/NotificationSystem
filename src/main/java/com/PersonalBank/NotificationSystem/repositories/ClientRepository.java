package com.PersonalBank.NotificationSystem.repositories;

import com.PersonalBank.NotificationSystem.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}