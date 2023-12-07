package com.knu.app.service.impl;

import com.knu.app.dto.error.ErrorDto;
import com.knu.app.dto.room.*;
import com.knu.app.entity.Room;
import com.knu.app.repository.ClientRepository;
import com.knu.app.repository.LocationRepository;
import com.knu.app.repository.RoomRepository;
import com.knu.app.service.RoomService;
import com.knu.app.util.KeycloakRequests;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.CorePublisher;
import reactor.core.publisher.Mono;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final LocationRepository locationRepository;
    private final ClientRepository clientRepository;

    @Override
    public Mono<ResponseEntity<Mono<?>>> createRoom(CreateRoomDto createRoomDto) {
        String clientEmail = KeycloakRequests.getEmail(createRoomDto.token().accessToken());

        if (clientEmail != null) {
            return locationRepository.existsLocationById(createRoomDto.locationId())
                    .flatMap( exists -> {
                        if (exists) {
                            return Mono.just(new ResponseEntity<>(
                                    clientRepository.findByEmail(clientEmail)
                                            .flatMap(client -> roomRepository.save(
                                                    Room.builder()
                                                            .locationId(createRoomDto.locationId())
                                                            .name(createRoomDto.name())
                                                            .build()
                                                    ).flatMap(room -> Mono.empty())
                                            ),
                                    HttpStatusCode.valueOf(200)
                            ));
                        } else {
                            return Mono.just(new ResponseEntity<>(
                                    Mono.just(
                                            new ErrorDto(
                                                    Collections.singletonList("Location does not exist")
                                            )
                                    ),
                                    HttpStatusCode.valueOf(400)
                            ));
                        }
                    });
        } else {
            return Mono.just(new ResponseEntity<>(
                    Mono.just(new ErrorDto(Collections.singletonList("Invalid token"))),
                    HttpStatusCode.valueOf(400)
            ));
        }
    }

    @Override
    public Mono<ResponseEntity<?>> getRoomList(GetRoomListDto getRoomListDto) {
        String clientEmail = KeycloakRequests.getEmail(getRoomListDto.token().accessToken());

        if (clientEmail != null) {
            return locationRepository.existsLocationById(getRoomListDto.locationId())
                    .flatMap( exists -> {
                        if (exists) {
                            return Mono.just(new ResponseEntity<>(
                                    roomRepository.findAllByLocationId(getRoomListDto.locationId())
                                            .map(room -> new RoomDto(room.getId(), room.getName())),
                                    HttpStatusCode.valueOf(200)
                            ));
                        } else {
                            return Mono.just(new ResponseEntity<>(
                                    Mono.just(
                                            new ErrorDto(
                                                    Collections.singletonList("Location does not exist")
                                            )
                                    ),
                                    HttpStatusCode.valueOf(400)
                            ));
                        }
                    });
        } else {
            return Mono.just(new ResponseEntity<>(
                    Mono.just(new ErrorDto(Collections.singletonList("Invalid token"))),
                    HttpStatusCode.valueOf(400)
            ));
        }
    }

    @Override
    public ResponseEntity<Mono<?>> editRoom(EditRoomDto editRoomDto) {
        String clientEmail = KeycloakRequests.getEmail(editRoomDto.token().accessToken());

        if (clientEmail != null) {
            return new ResponseEntity<>(
                    roomRepository.updateRoomById(
                            editRoomDto.room().id(),
                            editRoomDto.room().name()
                    ),
                    HttpStatusCode.valueOf(200)
            );
        } else {
            return new ResponseEntity<>(
                    Mono.just(new ErrorDto(Collections.singletonList("Invalid token"))),
                    HttpStatusCode.valueOf(400)
            );
        }
    }

    @Override
    public ResponseEntity<Mono<?>> deleteRoom(DeleteRoomDto deleteRoomDto) {
        String clientEmail = KeycloakRequests.getEmail(deleteRoomDto.token().accessToken());

        if (clientEmail != null) {
            return new ResponseEntity<>(
                    roomRepository.deleteRoomById(deleteRoomDto.id()),
                    HttpStatusCode.valueOf(200)
            );
        } else {
            return new ResponseEntity<>(
                    Mono.just(new ErrorDto(Collections.singletonList("Invalid token"))),
                    HttpStatusCode.valueOf(400)
            );
        }
    }
}
