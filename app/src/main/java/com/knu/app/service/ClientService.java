package com.knu.app.service;

import com.knu.app.dto.client.ClientAuthTokenDto;
import com.knu.app.dto.client.ClientLoginDto;
import com.knu.app.dto.client.ClientRegisterDto;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface ClientService {
    ResponseEntity<Mono<?>> registerClient(ClientRegisterDto clientRegisterDto);

    ResponseEntity<Mono<?>> loginClient(ClientLoginDto clientLoginDto);

    ResponseEntity<Mono<?>> logoutClient(ClientAuthTokenDto clientAuthTokenDto);

    ResponseEntity<Mono<?>> getClientDetails(ClientAuthTokenDto clientAuthTokenDto);

//    Mono<ClientDto> getClient(Integer clientId);
//
//    Mono<ClientDto> updateClient(Integer clientId, ClientDto clientDto);
//
//    Mono<Void> deleteClient(Integer clientId);
//
//    Flux<ClientDto> getAllClients();
}
