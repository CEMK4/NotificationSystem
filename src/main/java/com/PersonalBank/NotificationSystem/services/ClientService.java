package com.PersonalBank.NotificationSystem.services;

import com.PersonalBank.NotificationSystem.entities.Client;
import com.PersonalBank.NotificationSystem.entities.DTO.ClientDto;
import com.PersonalBank.NotificationSystem.repositories.ClientRepository;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Transactional
    public Client createClient(ClientDto client)
    {
        var clientData = new Client(client.getFullName(), client.getDateOfBirth());
        return clientRepository.save(clientData);
    }

    public Long checkClientExisting(ClientDto client)
    {
        var existingClient = clientRepository.findByFullNameAndDateOfBirth(client.getFullName(), client.getDateOfBirth());
        if (existingClient != null)
            return existingClient.getId();
        return null;
    }

    public void init() {
        clientRepository.save(new Client("Болдырев Артём Алексеевич", new Date("2002-09-29")));
    }
}