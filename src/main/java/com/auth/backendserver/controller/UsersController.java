package com.auth.backendserver.controller;

import com.auth.backendserver.model.Users;
import com.auth.backendserver.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;
    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }
    // create a user
    @PostMapping("/register")
    public ResponseEntity<Users> saveUser(@RequestBody Users user) {
        Users savedUser = usersService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }
    // get all users
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers(){
        List<Users> users = usersService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable UUID id) {
        Optional<Users> user = usersService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<Users> getUserByEmail(@PathVariable String email) {
        Optional<Users> user = usersService.getUserByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        usersService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


}
