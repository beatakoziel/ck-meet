package com.meet.ck.controllers;

import com.meet.ck.controllers.requests.AuthRequest;
import com.meet.ck.database.entities.User;
import com.meet.ck.database.enums.RegistrationStatus;
import com.meet.ck.services.AuthService;
import com.meet.ck.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.meet.ck.controllers.converters.UserConverter.userAuthToEntity;

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
    public ResponseEntity<String> basicLogin(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.basicLogin(request));
    }
}
