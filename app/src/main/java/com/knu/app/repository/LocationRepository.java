package com.knu.app.repository;

import com.knu.app.entity.Location;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LocationRepository extends R2dbcRepository<Location, Integer> {
    @Query("select id, name from location where client_id=$1")
    Flux<Location> findAllByClientId(Integer clientId);

    @Query("update location set name=$2 where id=$1")
    Mono<Object> updateLocationById(Integer id, String name);

    @Query("delete from location where id=$1")
    Mono<Object> deleteLocationById(Integer id);
}
