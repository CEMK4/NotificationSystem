/**
 * The ClientService class provides methods for managing clients in the system.
 */
package com.PersonalBank.NotificationSystem.services;

import com.PersonalBank.NotificationSystem.entities.Client;
import com.PersonalBank.NotificationSystem.entities.DTO.ClientDto;
import com.PersonalBank.NotificationSystem.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Service class for managing clients.
 */
@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    /**
     * Retrieves all clients from the database.
     *
     * @return List of all clients
     */
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    /**
     * Creates a new client based on the provided data.
     *
     * @param client Data of the client to create
     * @return Newly created client
     */
    public Client createClient(ClientDto client) {
        var clientData = new Client(client.getFullName(), client.getDateOfBirth());
        return clientRepository.save(clientData);
    }

    /**
     * Checks if a client with the given data already exists in the system.
     *
     * @param client Data of the client to check
     * @return ID of the existing client if found, null otherwise
     */
    public Long checkClientExisting(ClientDto client) {
        var existingClient = clientRepository.findByFullNameAndDateOfBirth(client.getFullName(), client.getDateOfBirth());
        if (existingClient != null)
            return existingClient.getId();
        return null;
    }

    /**
     * Initializes the client service with some initial data.
     */
    public void init() {
        var dob = LocalDate.of(2002, 9, 29);
        clientRepository.save(new Client("Болдырев Артём Алексеевич", dob));
    }

    /**
     * Writes information about clients to a file.
     */
    public void write() {
        clearLogsFolder();
        var text = new StringBuilder();

        for (var client : clientRepository.findAll()) {
            text.append("Имя клиента: ").append(client.getFullName());
            text.append("\nДата рождения : ").append(client.getDateOfBirth());
            if (!client.getBankCards().isEmpty()) {
                text.append("\nИмеющиеся карты: ");
                for (var card : client.getBankCards())
                    text.append("\n").append(card.getCardNumber());
                text.append("\n");
            }
        }

        String directoryName = "logs";
        String fileName = "logs/_users.txt";

        try {
            File directory = new File(directoryName);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File(fileName);

            if (file.exists())
                file.delete();
            file.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(text.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears the logs folder by deleting all files inside it.
     */
    private void clearLogsFolder() {
        String folderPath = "logs";

        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files)
                if (file.isFile())
                    file.delete();
        }
    }
}
