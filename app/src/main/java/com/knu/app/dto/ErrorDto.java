package com.knu.app.dto;

import java.util.List;

public record ErrorDto(
        List<String> errors
) {
}
