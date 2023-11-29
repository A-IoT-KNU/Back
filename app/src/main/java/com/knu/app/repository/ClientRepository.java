package com.knu.app.repository;

import com.knu.app.entity.ClientEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ClientRepository extends R2dbcRepository<ClientEntity, Integer> {
}
