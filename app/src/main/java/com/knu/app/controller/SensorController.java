package com.knu.app.controller;

import com.knu.app.dto.location.CreateLocationDto;
import com.knu.app.dto.sensor.CreateSensorDto;
import com.knu.app.service.RoomService;
import com.knu.app.service.SensorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sensor")
@CrossOrigin(origins = "https://localhost:4200/")
public class SensorController {

    private final SensorService sensorService;

    @PostMapping("/create")
    Mono<ResponseEntity<Mono<?>>> createSensor(@Valid @RequestBody CreateSensorDto createSensorDto) {
        return sensorService.createSensor(createSensorDto);
    }


}
