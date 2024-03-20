package com.PersonalBank.NotificationSystem.controllers;

import com.PersonalBank.NotificationSystem.entities.Client;
import com.PersonalBank.NotificationSystem.entities.DTO.ClientDto;
import com.PersonalBank.NotificationSystem.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing clients.
 */
@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    /**
     * Endpoint to retrieve all clients.
     *
     * @return ResponseEntity containing a list of clients if successful, or an error message if failed.
     */
    @GetMapping
    public ResponseEntity<?> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    /**
     * Endpoint to create a new client.
     *
     * @param client The client information to create.
     * @return ResponseEntity containing the created client if successful, or the existing client ID if the client already exists, or an error message if failed.
     */
    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody ClientDto client) {
        var clientId = clientService.checkClientExisting(client);
        if (clientId != null)
            return ResponseEntity.ok(clientId);
        Client createdClient = clientService.createClient(client);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

}
