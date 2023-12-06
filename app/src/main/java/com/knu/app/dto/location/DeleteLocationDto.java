package com.knu.app.dto.location;

import com.knu.app.dto.client.ClientAuthTokenDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DeleteLocationDto(
        @Valid
        ClientAuthTokenDto token,

        @NotNull(message = "Location id cannot be empty")
        @Min(value = 1, message = "Location id must be greater than zero")
        Integer id
) {
}
