package com.knu.app.repository;

import com.knu.app.entity.Client;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface ClientRepository extends R2dbcRepository<Client, Integer> {
    @Query("select id from client where email=$1")
    Mono<Client> findByEmail(String email);
}
