package com.knu.app.repository;

import com.knu.app.entity.Location;
import com.knu.app.entity.Room;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoomRepository extends R2dbcRepository<Room, Integer> {
    @Query("select id, name from room where location_id=$1")
    Flux<Room> findAllByLocationId(Integer locationId);

    @Query("update room set name=$2 where id=$1")
    Mono<Object> updateRoomById(Integer id, String name);

    @Query("delete from room where id=$1")
    Mono<Object> deleteRoomById(Integer id);

    @Query("delete from room where location_id=$1")
    Mono<Object> deleteRoomsByLocationId(Integer locationId);
}
