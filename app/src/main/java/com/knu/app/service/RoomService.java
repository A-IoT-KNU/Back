package com.knu.app.service;

import com.knu.app.dto.room.CreateRoomDto;
import com.knu.app.dto.room.DeleteRoomDto;
import com.knu.app.dto.room.EditRoomDto;
import com.knu.app.dto.room.GetRoomListDto;
import org.springframework.http.ResponseEntity;
import reactor.core.CorePublisher;
import reactor.core.publisher.Mono;

public interface RoomService {
    Mono<ResponseEntity<Mono<?>>> createRoom(CreateRoomDto createRoomDto);

    Mono<ResponseEntity<?>> getRoomList(GetRoomListDto getRoomListDto);

    ResponseEntity<Mono<?>> editRoom(EditRoomDto editRoomDto);
//
    ResponseEntity<Mono<?>> deleteRoom(DeleteRoomDto deleteRoomDto);
}
