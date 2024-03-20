package com.PersonalBank.NotificationSystem.entities.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for representing Client information.
 */
@Data
public class ClientDto implements Serializable {
    /**
     * The full name of the client.
     */
    @NotNull
    @NotEmpty
    @NotBlank
    String fullName;

    /**
     * The date of birth of the client.
     */
    @NotNull
    LocalDate dateOfBirth;
}
