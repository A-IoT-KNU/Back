package com.knu.app.controller;

import com.knu.app.dto.client.ClientAuthTokenDto;
import com.knu.app.dto.room.CreateRoomDto;
import com.knu.app.dto.room.DeleteRoomDto;
import com.knu.app.dto.room.EditRoomDto;
import com.knu.app.dto.room.GetRoomListDto;
import com.knu.app.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/room")
@CrossOrigin(origins = "https://localhost:4200/")
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/create")
    Mono<ResponseEntity<Mono<?>>> createRoom(@Valid @RequestBody CreateRoomDto createRoomDto) {
        return roomService.createRoom(createRoomDto);
    }

    @PostMapping("/list")
    Mono<ResponseEntity<?>> getLocationList(@Valid @RequestBody GetRoomListDto getRoomListDto) {
        return roomService.getRoomList(getRoomListDto);
    }

    @PutMapping("/edit")
    ResponseEntity<?> editLocation(@Valid @RequestBody EditRoomDto editRoomDto) {
        return roomService.editRoom(editRoomDto);
    }

    @DeleteMapping("/delete")
    ResponseEntity<?> deleteLocation(@Valid @RequestBody DeleteRoomDto deleteRoomDto) {
        return roomService.deleteRoom(deleteRoomDto);
    }
}
