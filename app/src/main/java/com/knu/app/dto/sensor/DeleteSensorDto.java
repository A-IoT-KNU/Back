package com.knu.app.dto.sensor;

import com.knu.app.dto.client.ClientAuthTokenDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DeleteSensorDto (
        @Valid
        ClientAuthTokenDto token,

        @NotNull(message = "Sensor id cannot be empty")
        @Min(value = 1, message = "Sensor id must be greater than zero")
        Integer id
) {
}
