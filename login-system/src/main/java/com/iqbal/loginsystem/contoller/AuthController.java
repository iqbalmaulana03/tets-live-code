package com.iqbal.loginsystem.contoller;

import com.iqbal.loginsystem.model.LoginRequest;
import com.iqbal.loginsystem.model.LoginResponse;
import com.iqbal.loginsystem.model.RegisterRequest;
import com.iqbal.loginsystem.model.RegisterResponse;
import com.iqbal.loginsystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse loginResponse = service.login(request);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/register")
    ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request){
        RegisterResponse registerResponse = service.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);
    }
}