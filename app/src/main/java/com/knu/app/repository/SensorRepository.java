package com.knu.app.repository;

import com.knu.app.entity.Sensor;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SensorRepository extends R2dbcRepository<Sensor, Integer> {


    @Query("select id, name from sensor where room_id=$1")
    Flux<Sensor> findAllByRoomId(Integer roomId);

    @Query("update sensor set name=$2 where id=$1")
    Mono<Object> updateSensorById(Integer id, String name);

    @Query("delete from sensor where id=$1")
    Mono<Object> deleteSensorById(Integer id);

    @Query("delete from sensor where room_id=$1")
    Mono<Object> deleteSensorByRoomId(Integer roomId);

    @Query("select exists(select 1 from room where id=$1)")
    Mono<Boolean> existsSensorById(Integer id);
}
