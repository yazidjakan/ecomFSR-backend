package com.ecom.clients_service.controller;

import com.ecom.clients_service.dto.Security.RegisterDto;
import com.ecom.clients_service.entity.Security.AuthentificationRequest;
import com.ecom.clients_service.entity.Security.AuthentificationResponse;
import com.ecom.clients_service.service.impl.Security.AuthentificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AuthController {

    private final AuthentificationService authenticationService;



    @PostMapping("/login")
    public ResponseEntity<AuthentificationResponse> authenticate(@RequestBody AuthentificationRequest request){
        System.out.println("Received authentication request for username: " + request.getUsername());
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authenticationService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
