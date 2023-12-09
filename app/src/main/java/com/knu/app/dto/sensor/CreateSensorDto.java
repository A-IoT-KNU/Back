package com.knu.app.dto.sensor;

import com.knu.app.dto.client.ClientAuthTokenDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateSensorDto(
        @Valid
        ClientAuthTokenDto token,

        @NotBlank(message = "Sensor name cannot be empty")
        String sensorName,

        @NotNull(message = "Location id cannot be empty")
        @Min(value = 1, message = "Location id must be greater than zero")
        Integer locationId,

        @NotNull(message = "Room id cannot be empty")
        @Min(value = 1, message = "Room id must be greater than zero")
        Integer roomId,

        @NotNull(message = "Sensor types cannot be empty")
        String sensorTypes
) {
}
