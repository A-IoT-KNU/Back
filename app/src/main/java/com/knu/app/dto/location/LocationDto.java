package com.knu.app.dto.location;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LocationDto(

        @NotNull(message = "Location id cannot be empty")
        @Min(value = 1, message = "Location id must be greater than zero")
        Integer id,

        @NotBlank(message = "Location name cannot be empty")
        String name
) {
}
