package com.knu.app.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClientRegisterDto(
    @NotBlank(message = "First name cannot be empty")
    @Size(min = 3, message = "First name length should have more than 3 characters")
    String firstName,

    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 3, message = "Last name length should have more than 3 characters")
    String lastName,

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    String email,

    @NotBlank(message = "Username cannot be empty")
    @Pattern(message = "Username is not valid", regexp = "^[A-Za-z0-9_]{3,}$")
    String username,

    @NotBlank(message = "Password cannot be empty")
    @Pattern(message = "Password is not valid", regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[!#@$&%^*]).{8,30}$")
    String password
) {
}
