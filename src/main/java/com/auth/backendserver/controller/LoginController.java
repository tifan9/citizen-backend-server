package com.auth.backendserver.controller;

import com.auth.backendserver.dto.LoginRequest;
import com.auth.backendserver.model.Users;
import com.auth.backendserver.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final UsersService usersService;

    @Autowired
    public LoginController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/login")
    public ResponseEntity<Users> login(@RequestBody LoginRequest loginRequest) {
        Optional<Users> user = usersService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(401).build());
    }
}