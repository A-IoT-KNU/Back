package com.knu.app.service;

import com.knu.app.dto.client.ClientAuthTokenDto;
import com.knu.app.dto.location.CreateLocationDto;
import com.knu.app.dto.location.DeleteLocationDto;
import com.knu.app.dto.location.EditLocationDto;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface LocationService {

    ResponseEntity<Mono<?>> createLocation(CreateLocationDto createLocationDto);

    ResponseEntity<?> getLocationList(ClientAuthTokenDto tokenDto);

    ResponseEntity<Mono<?>> editLocation(EditLocationDto editLocationDto);

    ResponseEntity<Mono<?>> deleteLocation(DeleteLocationDto deleteLocationDto);
}
