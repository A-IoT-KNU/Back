package com.knu.app.controller;

import com.knu.app.dto.sensor.CreateSensorDto;
import com.knu.app.dto.sensor.DeleteSensorDto;
import com.knu.app.dto.sensor.EditSensorDto;
import com.knu.app.dto.sensor.GetSensorListDto;
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

    @PostMapping("/list")
    Mono<ResponseEntity<?>> getLocationList(@Valid @RequestBody GetSensorListDto getSensorListDto) {
        return sensorService.getSensorList(getSensorListDto);
    }

    @PutMapping("/edit")
    ResponseEntity<?> editLocation(@Valid @RequestBody EditSensorDto editSensorDto) {
        return sensorService.editSensor(editSensorDto);
    }

    @DeleteMapping("/delete")
    ResponseEntity<?> deleteLocation(@Valid @RequestBody DeleteSensorDto deleteSensorDto) {
        return sensorService.deleteSensor(deleteSensorDto);
    }

}
