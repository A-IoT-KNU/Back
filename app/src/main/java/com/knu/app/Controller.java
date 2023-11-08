package com.knu.app;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class Controller {

    @GetMapping("/user")
    @PreAuthorize("hasRole('client_user')")
    public String helloUser() {
        return "Hello, user!";
    }
}
