package com.hdi.identity_service.controller;

import com.hdi.identity_service.dto.request.UserCreationRequest;
import com.hdi.identity_service.dto.request.UserUpdateRequest;
import com.hdi.identity_service.entity.User;
import com.hdi.identity_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    User createUser(@RequestBody @Valid UserCreationRequest request) {
       return userService.createRequest(request);
    }

    @GetMapping
    List<User> getAllUsers() {
    return userService.getUsers();
    }

    @GetMapping("/{userId}")
    User getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    User udateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "User deleted";
    }
}
