package com.knu.app.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClientLoginDto(
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, message = "Username length should have more than 3 characters")
    @Pattern(message = "Username is not valid", regexp = "^[a-zA-Z0-9_]{3,}$")
    String username,

    @NotBlank(message = "Password cannot be empty")
    @Pattern(message = "Password is not valid", regexp = "^(?=.*[A-Z])(?=.*[\\W])(?=.*[0-9])(?=.*[a-z]).{8,30}$")
    String password
) {
}
