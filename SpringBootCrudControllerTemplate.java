/**
 * Production-Ready Spring Boot REST Controller Template
 * Author: SpectraOne Solutions (https://spectraonesolutions.com)
 * Description: Clean architecture pattern demonstrating REST CRUD operations, DTOs, and ResponseEntity.
 */

package com.spectraone.handbook.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

// Sample DTO Records (Modern Java Feature)
record UserResponse(Long id, String name, String email, Instant createdAt) {}
record UserCreateRequest(String name, String email) {}
record ApiResponse<T>(boolean success, String message, T data) {}

@RestController
@RequestMapping("/api/v1/users")
public class SpringBootCrudControllerTemplate {

    // 1. GET ALL USERS
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        List<UserResponse> mockUsers = List.of(
            new UserResponse(1L, "Alice Johnson", "alice@example.com", Instant.now()),
            new UserResponse(2L, "Bob Smith", "bob@example.com", Instant.now())
        );
        return ResponseEntity.ok(new ApiResponse<>(true, "Users retrieved successfully", mockUsers));
    }

    // 2. GET USER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable Long id) {
        UserResponse user = new UserResponse(id, "Alice Johnson", "alice@example.com", Instant.now());
        return ResponseEntity.ok(new ApiResponse<>(true, "User found", user));
    }

    // 3. CREATE NEW USER
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@RequestBody UserCreateRequest request) {
        UserResponse createdUser = new UserResponse(101L, request.name(), request.email(), Instant.now());
        return new ResponseEntity<>(
            new ApiResponse<>(true, "User created successfully", createdUser), 
            HttpStatus.CREATED
        );
    }

    // 4. DELETE USER
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        // Business logic for deletion
        return ResponseEntity.ok(new ApiResponse<>(true, "User with ID " + id + " deleted.", null));
    }
}
