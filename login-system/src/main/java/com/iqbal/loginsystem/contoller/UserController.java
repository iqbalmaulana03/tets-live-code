package com.iqbal.loginsystem.contoller;

import com.iqbal.loginsystem.model.UserResponse;
import com.iqbal.loginsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    ResponseEntity<List<UserResponse>> dashboard(){
        List<UserResponse> dashboard = service.dashboard();

        return ResponseEntity.ok(dashboard);
    }
}
