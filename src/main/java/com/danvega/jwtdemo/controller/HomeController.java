package com.danvega.jwtdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {
    @GetMapping
    public String home(Principal principal){
        return "Hello JWT user "+principal.getName();
    }
}
