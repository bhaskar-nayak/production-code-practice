package com.production.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import com.production.dto.AuthRequest;
import com.production.dto.TokenResponse;
import com.production.model.User;
import com.production.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Builder
public class AuthController {
	@Autowired
    private AuthenticationManager authenticationManager;
	@Autowired
    private JwtService jwtService;
	@Autowired
    private com.production.repo.UserRepository userRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ROLE_USER")
                .build();

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername()).get();
        String token = jwtService.generateToken(user.getUsername());
        System.out.println("Generated JWT: " + token);
        return ResponseEntity.ok(new TokenResponse(token));
    }

}
