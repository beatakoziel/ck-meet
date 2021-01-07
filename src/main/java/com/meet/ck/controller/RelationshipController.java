package com.meet.ck.controller;

import com.meet.ck.controller.converters.RelationshipConverter;
import com.meet.ck.controller.response.RelationshipResponse;
import com.meet.ck.database.entities.User;
import com.meet.ck.database.enums.RelationStatus;
import com.meet.ck.service.RelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/relationship")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class RelationshipController {

    private final RelationshipService relationshipService;

    @GetMapping
    public ResponseEntity<List<RelationshipResponse>> getRelationships(Authentication authentication) {
        List<RelationshipResponse> list = relationshipService.getUsersRelations(getUsernameFromAuth(authentication))
                .stream()
                .map(RelationshipConverter::entityToResponse)
                .collect(Collectors.toList());
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<RelationStatus> sayHello(Authentication authentication, @PathVariable("userId") Long userId) {
        RelationStatus status = relationshipService.sayHello(getUsernameFromAuth(authentication), userId);
        return new ResponseEntity(status, HttpStatus.OK);
    }

    @PutMapping("/revert/{userId}")
    public ResponseEntity<RelationStatus> revertHello(Authentication authentication, @PathVariable("userId") Long userId) {
        relationshipService.revertHello(getUsernameFromAuth(authentication), userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    private String getUsernameFromAuth(Authentication authentication) {
        return ((User) authentication.getPrincipal()).getUsername();
    }
}
