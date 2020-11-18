package com.meet.ck.controllers;

import com.meet.ck.controllers.converters.UserConverter;
import com.meet.ck.controllers.requests.UserRequest;
import com.meet.ck.controllers.response.UserResponse;
import com.meet.ck.database.entities.User;
import com.meet.ck.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.meet.ck.controllers.converters.UserConverter.requestToUpdate;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

    private final IUserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        return new ResponseEntity(
                userService.getUsersList().stream().map(UserConverter::entityToResponse),
                HttpStatus.OK
        );
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
