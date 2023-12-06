package com.knu.app.service.impl;

import com.knu.app.dto.client.ClientAuthTokenDto;
import com.knu.app.dto.client.ClientLoginDto;
import com.knu.app.dto.client.ClientRegisterDto;
import com.knu.app.dto.error.ErrorDto;
import com.knu.app.repository.ClientRepository;
import com.knu.app.entity.Client;
import com.knu.app.service.ClientService;
import com.knu.app.util.KeycloakRequests;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public ResponseEntity<Mono<?>> registerClient(ClientRegisterDto clientRegisterDto) {
        String accessToken = KeycloakRequests.generateMasterAccessToken();

        if (accessToken != null) {
             var registerResponseEntity = KeycloakRequests.register(
                     accessToken,
                     clientRegisterDto
             );

             if (registerResponseEntity.getStatusCode() == HttpStatusCode.valueOf(200)) {
                 var loginResponseEntity = KeycloakRequests.login(
                         new ClientLoginDto(clientRegisterDto.username(), clientRegisterDto.password())
                 );

                 return new ResponseEntity<>(
                         clientRepository.save(
                                 Client.builder()
                                         .email(clientRegisterDto.email())
                                         .build()
                         ).mapNotNull(client -> (ClientAuthTokenDto) loginResponseEntity.getBody()),
                         HttpStatusCode.valueOf(200)
                 );
             } else {
                 return (ResponseEntity<Mono<?>>) registerResponseEntity;
             }
        }

        return new ResponseEntity<>(
                    Mono.just(new ErrorDto(Collections.singletonList("Internal server error"))),
                    HttpStatusCode.valueOf(500)
        );
    }

    @Override
    public ResponseEntity<Mono<?>> loginClient(ClientLoginDto clientLoginDto) {
       var loginResponseEntity = KeycloakRequests.login(clientLoginDto);

        if (loginResponseEntity.getStatusCode() == HttpStatusCode.valueOf(200)) {
            return new ResponseEntity<>(
                    Mono.just( (ClientAuthTokenDto) loginResponseEntity.getBody()),
                    HttpStatusCode.valueOf(200)
            );
        } else {
            return (ResponseEntity<Mono<?>>) loginResponseEntity;
        }
    }

    @Override
    public ResponseEntity<Mono<?>> logoutClient(ClientAuthTokenDto clientAuthTokenDto) {
        return (ResponseEntity<Mono<?>>) KeycloakRequests.logout(clientAuthTokenDto.refreshToken());
    }

//        @Override
//    public Mono<ClientDto> getClient(Integer clientId) {
////        return clientRepository.findById(clientId)
////                .map(clientEntity -> new ClientDto(clientEntity.getId(), clientEntity.getEmail()));
//    }
//
//    @Override
//    public Mono<ClientDto> updateClient(Integer clientId, ClientDto clientDto) {
//        return clientRepository.save(ClientEntity.builder()
//                .id(clientId)
//                .email(clientDto.email())
//                .build())
//                .map(clientEntity -> new ClientDto(clientEntity.getId(), clientEntity.getEmail()));
//    }
//
//    @Override
//    public Mono<Void> deleteClient(Integer clientId) {
//        return clientRepository.deleteById(clientId);
//    }
//
//    @Override
//    public Flux<ClientDto> getAllClients() {
//        return clientRepository.findAll()
//                .map(clientEntity -> new ClientDto(clientEntity.getId(), clientEntity.getEmail()));
//    }
}
