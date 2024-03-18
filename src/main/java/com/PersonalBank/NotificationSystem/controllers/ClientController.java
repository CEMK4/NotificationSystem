package com.PersonalBank.NotificationSystem.controllers;

import com.PersonalBank.NotificationSystem.entities.Client;
import com.PersonalBank.NotificationSystem.entities.DTO.ClientDto;
import com.PersonalBank.NotificationSystem.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<?> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody ClientDto client) {
        Client createdClient = clientService.createClient(client);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

}

