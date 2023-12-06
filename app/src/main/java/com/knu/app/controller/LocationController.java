package com.knu.app.controller;

import com.knu.app.dto.client.ClientAuthTokenDto;
import com.knu.app.dto.location.CreateLocationDto;
import com.knu.app.dto.location.DeleteLocationDto;
import com.knu.app.dto.location.EditLocationDto;
import com.knu.app.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/location")
@CrossOrigin(origins = "https://localhost:4200/")
public class LocationController {

    private final LocationService locationService;

    @PostMapping("/create")
    ResponseEntity<Mono<?>> createLocation(@Valid @RequestBody CreateLocationDto createLocationDto) {
        return locationService.createLocation(createLocationDto);
    }

    @GetMapping("/list")
    ResponseEntity<?> getLocationList(@Valid @RequestBody ClientAuthTokenDto tokenDto) {
        return locationService.getLocationList(tokenDto);
    }

    @PutMapping("/edit")
    ResponseEntity<?> editLocation(@Valid @RequestBody EditLocationDto editLocationDto) {
        return locationService.editLocation(editLocationDto);
    }

    @DeleteMapping("/delete")
    ResponseEntity<?> deleteLocation(@Valid @RequestBody DeleteLocationDto deleteLocationDto) {
        return locationService.deleteLocation(deleteLocationDto);
    }
}
