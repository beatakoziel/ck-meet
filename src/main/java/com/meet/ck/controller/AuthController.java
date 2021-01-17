package com.meet.ck.controller;

import com.meet.ck.controller.request.AuthRequest;
import com.meet.ck.controller.response.AuthResponse;
import com.meet.ck.service.AuthService;
import com.meet.ck.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.meet.ck.controller.converter.UserConverter.userAuthToEntity;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final IUserService userService;
    private final AuthService authService;

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody AuthRequest userRequest) {
        userService.registerUser(userAuthToEntity(userRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<AuthResponse> basicLogin(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.basicLogin(request));
    }
}
