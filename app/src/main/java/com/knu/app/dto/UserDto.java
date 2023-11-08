package com.knu.app.dto;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserDto {
    
    @NotBlank(message = "At least one name should be set")
    @Size(min = 3, message = "Name length should have more than 3 characters")
    String name;

    @NotBlank(message = "At least one surname should be set")
    @Size(min = 3, message = "Name length should have more than 3 characters")
    String surname;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    String email;

    @NotBlank(message = "Username cannot be empty")
    @Pattern(regexp = "^[A-Za-z0-9_]{3,}$")
    String username;

    @NotBlank(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[!#@$&%^*]).{8,30}$")
    String password;
}
