package com.PersonalBank.NotificationSystem.entities.DTO;

import jakarta.validation.constraints.*;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for representing BankCard information.
 */
@Value
public class BankCardDto implements Serializable {
    /**
     * The card number of the bank card.
     */
    @NotNull
    @Size(min = 16, max = 19)
    @Pattern(regexp = "\\b\\d{4} ?\\d{4} ?\\d{4} ?\\d{4}\\b")
    @NotEmpty
    @NotBlank
    @Length(min = 16)
    String cardNumber;

    /**
     * The issue date of the bank card.
     */
    @NotNull
    LocalDateTime issueDate;
}
