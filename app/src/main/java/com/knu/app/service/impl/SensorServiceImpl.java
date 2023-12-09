package com.knu.app.service.impl;

import com.knu.app.dto.error.ErrorDto;
import com.knu.app.dto.sensor.CreateSensorDto;
import com.knu.app.entity.Room;
import com.knu.app.entity.Sensor;
import com.knu.app.repository.ClientRepository;
import com.knu.app.repository.LocationRepository;
import com.knu.app.repository.RoomRepository;
import com.knu.app.repository.SensorRepository;
import com.knu.app.service.SensorService;
import com.knu.app.util.KeycloakRequests;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class SensorServiceImpl implements SensorService {

    private final ClientRepository clientRepository;
    private final LocationRepository locationRepository;
    private final RoomRepository roomRepository;
    private final SensorRepository sensorRepository;

    @Override
    public Mono<ResponseEntity<Mono<?>>> createSensor(CreateSensorDto createSensorDto) {
        String clientEmail = KeycloakRequests.getEmail(createSensorDto.token().accessToken());

        if (clientEmail != null) {
            return roomRepository.existsRoomById(createSensorDto.roomId())
                    .flatMap( exists -> {
                        if (exists) {
                            return Mono.just(new ResponseEntity<>(
                                    clientRepository.findByEmail(clientEmail)
                                            .flatMap(client -> sensorRepository.save(
                                                    Sensor.builder()
                                                            .roomId(createSensorDto.roomId())
                                                            .sensorName(createSensorDto.sensorName())
                                                            .sensorTypes(createSensorDto.sensorTypes())
                                                            .locationId(createSensorDto.locationId())
                                                            .build()
                                                    ).flatMap(sensor -> Mono.empty())
                                            ),
                                    HttpStatusCode.valueOf(200)
                            ));
                        } else {
                            return Mono.just(new ResponseEntity<>(
                                    Mono.just(
                                            new ErrorDto(
                                                    Collections.singletonList("Room does not exist")
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
}
