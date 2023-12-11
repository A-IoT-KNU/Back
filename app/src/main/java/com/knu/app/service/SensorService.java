package com.knu.app.service;

import com.knu.app.dto.sensor.CreateSensorDto;
import com.knu.app.dto.sensor.DeleteSensorDto;
import com.knu.app.dto.sensor.EditSensorDto;
import com.knu.app.dto.sensor.GetSensorListDto;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface SensorService {
    Mono<ResponseEntity<Mono<?>>> createSensor(CreateSensorDto createSensorDto);

    Mono<ResponseEntity<?>> getSensorList(GetSensorListDto getSensorListDto);

    ResponseEntity<Mono<?>> editSensor(EditSensorDto editSensorDto);

    ResponseEntity<Mono<?>> deleteSensor(DeleteSensorDto deleteSensorDto);
}
