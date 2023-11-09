package com.knu.app.service;

import com.knu.app.controller.dto.AuthTokenDto;
import com.knu.app.controller.dto.ClientDto;
import com.knu.app.controller.dto.ClientLoginDto;
import com.knu.app.controller.dto.ClientRegisterDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

public interface ClientService {
    Mono<AuthTokenDto> registerClient(ClientRegisterDto clientRegisterDto);

    Mono<AuthTokenDto> loginClient(ClientLoginDto clientLoginDto);

//    Mono<ClientDto> getClient(Integer clientId);
//
//    Mono<ClientDto> updateClient(Integer clientId, ClientDto clientDto);
//
//    Mono<Void> deleteClient(Integer clientId);
//
//    Flux<ClientDto> getAllClients();
}
