package com.knu.app.dto.sensor;

import com.knu.app.dto.client.ClientAuthTokenDto;
import jakarta.validation.Valid;

public record EditSensorDto(
    @Valid
    ClientAuthTokenDto token,
    @Valid
    SensorDto sensor
) {
}
