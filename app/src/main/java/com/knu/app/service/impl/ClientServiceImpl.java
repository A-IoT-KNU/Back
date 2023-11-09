package com.knu.app.service.impl;

import com.knu.app.controller.dto.AuthTokenDto;
import com.knu.app.controller.dto.ClientLoginDto;
import com.knu.app.controller.dto.ClientRegisterDto;
import com.knu.app.repository.entity.ClientEntity;
import com.knu.app.repository.ClientRepository;
import com.knu.app.request.KeycloakRequests;
import com.knu.app.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Mono<AuthTokenDto> registerClient(ClientRegisterDto clientRegisterDto) {
        String accessToken = KeycloakRequests.generateMasterAccessToken();

        if (accessToken != null) {
             Boolean success = KeycloakRequests.register(
                     accessToken,
                     clientRegisterDto.firstName(),
                     clientRegisterDto.lastName(),
                     clientRegisterDto.username(),
                     clientRegisterDto.email(),
                     clientRegisterDto.password()
             );

             if (success) {
                 ArrayList<String> tokens = (ArrayList<String>) KeycloakRequests.login(
                         clientRegisterDto.username(),
                         clientRegisterDto.password()
                 );

                 return clientRepository.save(ClientEntity.builder()
                                 .email(clientRegisterDto.email())
                                 .build())
                         .map(
                            clientEntity ->
                                    new AuthTokenDto(tokens.get(0), tokens.get(1))
                         );
             }
        }

        return Mono.empty();
    }

    @Override
    public Mono<AuthTokenDto> loginClient(ClientLoginDto clientLoginDto) {
        ArrayList<String> tokens = (ArrayList<String>) KeycloakRequests.login(
                clientLoginDto.username(),
                clientLoginDto.password()
        );

        if (tokens != null) {
            return Mono.just(new AuthTokenDto(tokens.get(0), tokens.get(1)));
        }

        return Mono.empty();
    }

//    @Override
//    public Mono<ClientDto> getClient(Integer clientId) {
//        return clientRepository.findById(clientId)
//                .map(clientEntity -> new ClientDto(clientEntity.getId(), clientEntity.getEmail()));
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
