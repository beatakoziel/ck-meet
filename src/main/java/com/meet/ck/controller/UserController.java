package com.meet.ck.controller;

import com.meet.ck.controller.converter.UserConverter;
import com.meet.ck.controller.request.PersonalDataRequest;
import com.meet.ck.controller.request.PersonalizationDataRequest;
import com.meet.ck.controller.response.UserResponse;
import com.meet.ck.database.entity.User;
import com.meet.ck.database.enums.RegistrationStatus;
import com.meet.ck.service.IUserService;
import com.meet.ck.service.RelationshipService;
import com.meet.ck.utility.AuthUsernameExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

import static com.meet.ck.controller.converter.UserConverter.entityToResponse;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

    private final IUserService userService;
    private final RelationshipService relationshipService;
    private final AuthUsernameExtractor extractor;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers(Authentication authentication) {
        String username = extractor.getUsernameFromAuth(authentication);
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
        UserResponse userResponse = UserConverter.loggedUserToResponse(userService.getUserByUsername(extractor.getUsernameFromAuth(authentication)));
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/avatar")
    public ResponseEntity<Void> uploadImage(Authentication auth, @RequestParam("image") MultipartFile file) {
        userService.uploadImage(extractor.getUsernameFromAuth(auth), file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/data")
    public ResponseEntity<Void> registerUserPersonalData(Authentication auth, @RequestBody PersonalDataRequest request) {
        userService.registerUserPersonalData(extractor.getUsernameFromAuth(auth), request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/personalization")
    public ResponseEntity<Void> registerUserPersonalizationData(Authentication auth, @RequestBody @Valid PersonalizationDataRequest request) {
        userService.registerUserPersonalizationData(extractor.getUsernameFromAuth(auth), request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{userId}/enable")
    public ResponseEntity<Void> updateUserEnabled(@PathVariable Long userId, boolean enabled) {
        userService.updateUserEnabled(userId, enabled);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteAccount(Authentication auth) {
        relationshipService.deleteUserWithRelations(extractor.getUsernameFromAuth(auth));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/status")
    public RegistrationStatus status(Authentication authentication) {
        String username  = extractor.getUsernameFromAuth(authentication);
        return userService.getUserStatus(username);
    }

}
