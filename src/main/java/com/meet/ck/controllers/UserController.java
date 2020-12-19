package com.meet.ck.controllers;

import com.meet.ck.controllers.converters.UserConverter;
import com.meet.ck.controllers.requests.PersonalDataRequest;
import com.meet.ck.controllers.requests.PersonalizationDataRequest;
import com.meet.ck.controllers.response.UserResponse;
import com.meet.ck.database.entities.User;
import com.meet.ck.database.enums.RegistrationStatus;
import com.meet.ck.services.IUserService;
import com.meet.ck.services.RelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

import static com.meet.ck.controllers.converters.UserConverter.entityToResponse;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

    private final IUserService userService;
    private final RelationshipService relationshipService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers(Authentication authentication) {
        String username = getUsernameFromAuth(authentication);
        return new ResponseEntity(
                userService.getUsersList().stream()
                        .map(user -> entityToResponse(user,
                                relationshipService.getUserRelationWithUser(username, user.getUsername()))),
                HttpStatus.OK
        );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @GetMapping("/current")
    public ResponseEntity<UserResponse> getCurrentUser(Authentication authentication) {
        UserResponse userResponse = UserConverter.loggedUserToResponse(userService.getUserByUsername(getUsernameFromAuth(authentication)));
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/avatar")
    public ResponseEntity<Void> uploadImage(Authentication auth, @RequestParam("image") MultipartFile file) {
        userService.uploadImage(getUsernameFromAuth(auth), file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/data")
    public ResponseEntity<Void> registerUserPersonalData(Authentication auth, @RequestBody PersonalDataRequest request) {
        userService.registerUserPersonalData(getUsernameFromAuth(auth), request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/personalization")
    public ResponseEntity<Void> registerUserPersonalizationData(Authentication auth, @RequestBody @Valid PersonalizationDataRequest request) {
        userService.registerUserPersonalizationData(getUsernameFromAuth(auth), request);
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

    @DeleteMapping()
    public ResponseEntity<Void> deleteAccount(Authentication auth) {
        relationshipService.deleteUserWithRelations(getUsernameFromAuth(auth));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/status")
    public RegistrationStatus status(Authentication authentication) {
        return userService.getUserStatus(getUsernameFromAuth(authentication));
    }

    private String getUsernameFromAuth(Authentication authentication) {
        return ((User) authentication.getPrincipal()).getUsername();
    }

}
