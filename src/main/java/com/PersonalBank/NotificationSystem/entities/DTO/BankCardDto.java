package com.PersonalBank.NotificationSystem.entities.DTO;

import jakarta.validation.constraints.*;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.PersonalBank.NotificationSystem.entities.BankCard}
 */
@Value
public class BankCardDto implements Serializable {
    @NotNull
    @Size(min = 16, max = 19)
    @Pattern(regexp = "\\b\\d{4} ?\\d{4} ?\\d{4} ?\\d{4}\\b")
    @NotEmpty
    @NotBlank
    @Length(min = 16)
    String cardNumber;
    @NotNull
    Date issueDate;
}