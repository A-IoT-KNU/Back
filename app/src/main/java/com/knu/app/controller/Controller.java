package com.knu.app.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/everyone")
    public String helloEveryone() {
        return "Hello Everyone from Spring Boot & Keycloak";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('client_user')")
    public String helloUser() {
        return "Hello User from Spring Boot & Keycloak";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('client_admin')")
    public String helloAdmin() {
        return "Hello Admin from Spring Boot & Keycloak";
    }
}
