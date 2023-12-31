package com.knu.app.controller;

import com.knu.app.dto.client.ClientAuthTokenDto;
import com.knu.app.dto.client.ClientLoginDto;
import com.knu.app.dto.client.ClientRegisterDto;
import com.knu.app.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "https://localhost:4200/")
public class ClientController {

    private final ClientService clientService;
    @PostMapping("/register")
    ResponseEntity<Mono<?>> registerClient(@Valid @RequestBody ClientRegisterDto clientRegisterDto) {
        return clientService.registerClient(clientRegisterDto);
    }
    
    @PostMapping("/login")
    ResponseEntity<Mono<?>> loginClient(@Valid @RequestBody ClientLoginDto clientLoginDto) {
        return clientService.loginClient(clientLoginDto);
    }

    @PostMapping("/logout")
    ResponseEntity<Mono<?>> logoutClient(@Valid @RequestBody ClientAuthTokenDto clientAuthTokenDto) {
        return clientService.logoutClient(clientAuthTokenDto);
    }

    @PostMapping("/details")
    ResponseEntity<Mono<?>> getClientDetails(@Valid @RequestBody ClientAuthTokenDto clientAuthTokenDto) {
        return clientService.getClientDetails(clientAuthTokenDto);
    }
//    @GetMapping("/{clientId}")
//    Mono<ClientDto> getClient(@PathVariable("clientId") Integer clientId) {
//        return clientService.getClient(clientId);
//    }
//
//    @PutMapping("/{clientId}")
//    Mono<ClientDto> updateClient(@PathVariable("clientId") Integer clientId, @RequestBody ClientDto clientDto) {
//        return clientService.updateClient(clientId, clientDto);
//    }
//
//    @DeleteMapping("/{clientId}")
//    Mono<Void> deleteClient(@PathVariable("clientId") Integer clientId) {
//        return clientService.deleteClient(clientId);
//    }
//
//    @GetMapping("/all")
//    Flux<ClientDto> getAllClients() {
//        return clientService.getAllClients();
//    }
}
