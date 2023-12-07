package com.knu.app.dto.room;

import com.knu.app.dto.client.ClientAuthTokenDto;
import jakarta.validation.Valid;

public record EditRoomDto(
    @Valid
    ClientAuthTokenDto token,

    @Valid
    RoomDto room
) {
}
