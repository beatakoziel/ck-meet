package com.meet.ck.controllers;

import com.meet.ck.controllers.requests.UserRequest;
import com.meet.ck.database.entities.User;
import com.meet.ck.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.meet.ck.controllers.UserConverter.requestToEntity;
import static com.meet.ck.controllers.UserConverter.requestToUpdate;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserRequest userRequest) {
        userService.createUser(requestToEntity(userRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsersList(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId, @Valid @RequestBody UserRequest userRequest) {
        userService.updateUser(requestToUpdate(userId, userRequest));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{userId}/enable")
    public ResponseEntity<Void> updateUserEnabled(@PathVariable Long userId, boolean enabled) {
        userService.updateUserEnabled(userId, enabled);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
