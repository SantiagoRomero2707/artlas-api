package com.artists.artlasapi.controller;

import com.artists.artlasapi.dto.auth.request.AuthenticationRequest;
import com.artists.artlasapi.dto.auth.request.RegisterRequest;
import com.artists.artlasapi.dto.auth.response.AuthenticationResponse;
import com.artists.artlasapi.dto.auth.response.RegisterResponse;
import com.artists.artlasapi.service.impl.AuthenticationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationServiceImpl service;

    public AuthenticationController(AuthenticationServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
