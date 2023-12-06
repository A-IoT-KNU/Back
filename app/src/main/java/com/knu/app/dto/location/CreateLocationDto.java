package com.knu.app.dto.location;

import com.knu.app.dto.client.ClientAuthTokenDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record CreateLocationDto(
        @Valid
        ClientAuthTokenDto token,

        @NotBlank(message = "Location name cannot be empty")
        String name
) {
}

