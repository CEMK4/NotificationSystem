package com.PersonalBank.NotificationSystem.repositories;

import com.PersonalBank.NotificationSystem.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}