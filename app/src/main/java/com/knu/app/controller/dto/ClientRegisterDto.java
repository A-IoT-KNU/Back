package com.knu.app.controller.dto;

public record ClientRegisterDto(
        String firstName,
        String lastName,
        String email,
        String username,
        String password
) {
}
