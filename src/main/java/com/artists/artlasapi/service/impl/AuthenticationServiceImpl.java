package com.artists.artlasapi.service.impl;

import com.artists.artlasapi.dto.auth.request.AuthenticationRequest;
import com.artists.artlasapi.dto.auth.request.RegisterRequest;
import com.artists.artlasapi.dto.auth.response.AuthenticationResponse;
import com.artists.artlasapi.dto.auth.response.RegisterResponse;
import com.artists.artlasapi.entity.auth.Role;
import com.artists.artlasapi.entity.auth.User;
import com.artists.artlasapi.repository.UserRepository;
import com.artists.artlasapi.security.jwt.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterResponse register(RegisterRequest request) {
        if(request.getRole() == null){

            var user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.valueOf("USER"))
                    .build();
            repository.save(user);

            var jwtToken = jwtService.generateToken(user);
            return RegisterResponse.builder()
                    .token(jwtToken)
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .authorities(user.getAuthorities())
                    .build();
        }

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole()))
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return RegisterResponse.builder()
                .token(jwtToken)
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .authorities(user.getAuthorities())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .authorities(user.getAuthorities())
                .build();
    }

    @Transactional
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

}