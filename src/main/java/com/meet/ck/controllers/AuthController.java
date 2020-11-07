package com.meet.ck.controllers;

import com.meet.ck.controllers.requests.AuthRequest;
import com.meet.ck.services.AuthService;
import com.meet.ck.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.meet.ck.controllers.converters.UserConverter.userAuthToEntity;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final IUserService userService;
    private final AuthService authService;

    @GetMapping("/test")
    public String test() {
        return "hello";
    }

    @PostMapping(value = "/signup", consumes = "application/json")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody AuthRequest userRequest) {
        userService.registerUser(userAuthToEntity(userRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<String> basicLogin(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.basicLogin(request));
    }
}
