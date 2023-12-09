package com.knu.app.service;


import com.knu.app.dto.sensor.CreateSensorDto;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface SensorService {

    Mono<ResponseEntity<Mono<?>>> createSensor(CreateSensorDto createSensorDto);
}
