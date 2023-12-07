package com.knu.app.dto.room;

import com.knu.app.dto.client.ClientAuthTokenDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateRoomDto(
        @Valid
        ClientAuthTokenDto token,

        @NotNull(message = "Location id cannot be empty")
        @Min(value = 1, message = "Location id must be greater than zero")
        Integer locationId,

        @NotBlank(message = "Room name cannot be empty")
        String name
) {
}
