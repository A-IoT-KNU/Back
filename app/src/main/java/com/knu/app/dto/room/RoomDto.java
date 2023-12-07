package com.knu.app.dto.room;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoomDto(
        @NotNull(message = "Room id cannot be empty")
        @Min(value = 1, message = "Room id must be greater than zero")
        Integer id,

        @NotBlank(message = "Room name cannot be empty")
        String name
) {
}
