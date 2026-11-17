package com.ec.ecommerceordermanagement.user.controller;

import com.ec.ecommerceordermanagement.user.dto.CreateUserRequest;
import com.ec.ecommerceordermanagement.user.dto.UserResponse;
import com.ec.ecommerceordermanagement.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller responsible for handling the User related HTTP requests
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Registers a new user
     *
     * @param request the user registration details
     * @return the registered user response
     */
    @PostMapping
    public ResponseEntity<UserResponse> registerUser(
            @Valid @RequestBody CreateUserRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.registerUser(request));
    }

    /**
     * Retrieves a user by their unique identifier
     *
     * @param userId the unique user ID of the user
     * @return the user response
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}
