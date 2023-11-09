package com.knu.app.controller;

import com.knu.app.controller.dto.AuthTokenDto;
import com.knu.app.controller.dto.ClientDto;
import com.knu.app.controller.dto.ClientLoginDto;
import com.knu.app.controller.dto.ClientRegisterDto;
import com.knu.app.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;


    @PostMapping("/register")
    Mono<AuthTokenDto> registerClient(@RequestBody ClientRegisterDto clientRegisterDto) {
        return clientService.registerClient(clientRegisterDto);
    }
    
    @PostMapping("/login")
    Mono<AuthTokenDto> loginClient(@RequestBody ClientLoginDto clientLoginDto) {
        return clientService.loginClient(clientLoginDto);
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
