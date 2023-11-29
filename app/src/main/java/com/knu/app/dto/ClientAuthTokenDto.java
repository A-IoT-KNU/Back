package com.knu.app.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientAuthTokenDto(
        @NotBlank(message = "Access token cannot be empty")
        String accessToken,

        @NotBlank(message = "Refresh token cannot be empty")
        String refreshToken
) {
}
