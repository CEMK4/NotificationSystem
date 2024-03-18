package com.PersonalBank.NotificationSystem.entities.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.PersonalBank.NotificationSystem.entities.Client}
 */
@Data
public class ClientDto implements Serializable {
    @NotNull
    @NotEmpty
    @NotBlank
    String fullName;
    @NotNull
    Date dateOfBirth;
}