package com.hdi.identity_service.controller;

import com.hdi.identity_service.dto.request.ApiResponse;
import com.hdi.identity_service.dto.request.UserCreationRequest;
import com.hdi.identity_service.dto.request.UserUpdateRequest;
import com.hdi.identity_service.dto.response.UserResponse;
import com.hdi.identity_service.entity.User;
import com.hdi.identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    private UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createRequest(request));
      return apiResponse;
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getAllUsers() {

     var authentication = SecurityContextHolder.getContext().getAuthentication();

     log.info("Usename: {}", authentication.getName());
     authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

    return ApiResponse.<List<UserResponse>>builder()
            .result(userService.getUsers())
            .build();
    }

    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    UserResponse udateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "User deleted";
    }
}
