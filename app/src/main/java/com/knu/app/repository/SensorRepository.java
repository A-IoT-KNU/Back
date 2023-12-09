package com.knu.app.repository;


import com.knu.app.entity.Sensor;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface SensorRepository extends R2dbcRepository<Sensor, Integer> {

}
