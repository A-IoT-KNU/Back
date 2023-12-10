package com.knu.app.dto.sensor;

import com.knu.app.dto.client.ClientAuthTokenDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record GetSensorListDto(
        @Valid
        ClientAuthTokenDto token,

        @NotNull(message = "Room id cannot be empty")
        @Min(value = 1, message = "Room id must be greater than zero")
        Integer roomId
) {
}
