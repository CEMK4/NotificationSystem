package com.PersonalBank.NotificationSystem.services;

import com.PersonalBank.NotificationSystem.entities.Client;
import com.PersonalBank.NotificationSystem.entities.DTO.ClientDto;
import com.PersonalBank.NotificationSystem.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client createClient(ClientDto client)
    {
        var cl = new Client(client.getFullName(), client.getDateOfBirth());
        return clientRepository.save(cl);
    }

}