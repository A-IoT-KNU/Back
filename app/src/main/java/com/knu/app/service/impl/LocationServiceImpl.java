package com.knu.app.service.impl;

import com.knu.app.dto.client.ClientAuthTokenDto;
import com.knu.app.dto.error.ErrorDto;
import com.knu.app.dto.location.CreateLocationDto;
import com.knu.app.dto.location.DeleteLocationDto;
import com.knu.app.dto.location.EditLocationDto;
import com.knu.app.dto.location.LocationDto;
import com.knu.app.entity.Location;
import com.knu.app.repository.ClientRepository;
import com.knu.app.repository.LocationRepository;
import com.knu.app.service.LocationService;
import com.knu.app.util.KeycloakRequests;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class LocationServiceImpl implements LocationService {

    private final ClientRepository clientRepository;
    private final LocationRepository locationRepository;

    @Override
    public ResponseEntity<Mono<?>> createLocation(CreateLocationDto createLocationDto) {
        String clientEmail = KeycloakRequests.getEmail(createLocationDto.token().accessToken());

        if (clientEmail != null) {
            return new ResponseEntity<>(
                    clientRepository.findByEmail(clientEmail)
                            .flatMap(client -> locationRepository.save(
                                    Location.builder()
                                            .clientId(client.getId())
                                            .name(createLocationDto.name())
                                            .build()
                            ).flatMap(location -> Mono.empty())
                    ),
                    HttpStatusCode.valueOf(200)
            );
        } else {
            return new ResponseEntity<>(
                    Mono.just(new ErrorDto(Collections.singletonList("Invalid token"))),
                    HttpStatusCode.valueOf(400)
            );
        }
    }

    @Override
    public ResponseEntity<?> getLocationList(ClientAuthTokenDto tokenDto) {
        String clientEmail = KeycloakRequests.getEmail(tokenDto.accessToken());

        if (clientEmail != null) {
            return new ResponseEntity<>(
                    clientRepository.findByEmail(clientEmail)
                            .flatMapMany(client -> locationRepository.findAllByClientId(client.getId()))
                            .map(location -> new LocationDto(location.getId(), location.getName())),
                    HttpStatusCode.valueOf(200)
            );
        } else {
            return new ResponseEntity<>(
                    Mono.just(new ErrorDto(Collections.singletonList("Invalid token"))),
                    HttpStatusCode.valueOf(400)
            );
        }
    }

    @Override
    public ResponseEntity<Mono<?>> editLocation(EditLocationDto editLocationDto) {
        String clientEmail = KeycloakRequests.getEmail(editLocationDto.token().accessToken());

        if (clientEmail != null) {
            return new ResponseEntity<>(
                    locationRepository.updateLocationById(
                            editLocationDto.location().id(),
                            editLocationDto.location().name()
                    ),
                    HttpStatusCode.valueOf(200)
            );
        } else {
            return new ResponseEntity<>(
                    Mono.just(new ErrorDto(Collections.singletonList("Invalid token"))),
                    HttpStatusCode.valueOf(400)
            );
        }
    }

    @Override
    public ResponseEntity<Mono<?>> deleteLocation(DeleteLocationDto deleteLocationDto) {
        String clientEmail = KeycloakRequests.getEmail(deleteLocationDto.token().accessToken());

        if (clientEmail != null) {
            return new ResponseEntity<>(
                    locationRepository.deleteLocationById(deleteLocationDto.id()),
                    HttpStatusCode.valueOf(200)
            );
        } else {
            return new ResponseEntity<>(
                    Mono.just(new ErrorDto(Collections.singletonList("Invalid token"))),
                    HttpStatusCode.valueOf(400)
            );
        }
    }
}
