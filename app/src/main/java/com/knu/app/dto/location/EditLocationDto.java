package com.knu.app.dto.location;

import com.knu.app.dto.client.ClientAuthTokenDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record EditLocationDto(
        @Valid
        ClientAuthTokenDto token,

        @Valid
        LocationDto location
) {
}
