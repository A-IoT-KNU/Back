package com.knu.app.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClientRegisterDto(
    @NotBlank(message = "First name cannot be empty")
    @Size(min = 3, message = "First name length should have more than 3 characters")
    @Pattern(message = "First name is not valid", regexp = "^(?=.*[a-zA-Z]).{3,}$")
    String firstName,

    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 3, message = "Last name length should have more than 3 characters")
    @Pattern(message = "Last name is not valid", regexp = "^(?=.*[a-zA-Z]).{3,}$")
    String lastName,

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    String email,

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, message = "Username length should have more than 3 characters")
    @Pattern(message = "Username is not valid", regexp = "^[a-zA-Z0-9_]{3,}$")
    String username,

    @NotBlank(message = "Password cannot be empty")
    @Pattern(message = "Password is not valid", regexp = "^(?=.*[A-Z])(?=.*[\\W])(?=.*[0-9])(?=.*[a-z]).{8,30}$")
    String password
) {
}
