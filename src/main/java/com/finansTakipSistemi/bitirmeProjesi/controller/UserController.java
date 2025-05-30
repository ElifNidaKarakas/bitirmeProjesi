package com.finansTakipSistemi.bitirmeProjesi.controller;

import com.finansTakipSistemi.bitirmeProjesi.model.User;
import com.finansTakipSistemi.bitirmeProjesi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/{id}/summary")
    public ResponseEntity<User> getUserSummary(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}