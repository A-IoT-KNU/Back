package com.knu.app.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthTokenDto (

        @NotBlank(message = "Access token cannot be empty")
        String accessToken,

        @NotBlank(message = "Refresh token cannot be empty")
        String refreshToken
) {
}
