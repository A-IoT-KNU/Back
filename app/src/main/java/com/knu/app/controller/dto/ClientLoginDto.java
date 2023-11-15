package com.knu.app.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClientLoginDto(

    @NotBlank(message = "Username cannot be empty")
    @Pattern(message = "Username is not valid", regexp = "^[A-Za-z0-9_]{3,}$")
    String username,

    @NotBlank(message = "Password cannot be empty")
    @Pattern(message = "Password is not valid", regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[!#@$&%^*]).{8,30}$")
    String password
) {
}
