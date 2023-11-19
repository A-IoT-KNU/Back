package com.knu.app.controller.dto;

import java.util.List;

public record ErrorDto(
        List<String> errors
) {
}
