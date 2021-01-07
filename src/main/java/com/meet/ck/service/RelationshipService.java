package com.meet.ck.service;

import com.meet.ck.database.entities.Relationship;
import com.meet.ck.database.entities.User;
import com.meet.ck.database.enums.RelationStatus;
import com.meet.ck.database.repositories.IRelationshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelationshipService {
    private final IRelationshipRepository relationshipRepository;
    private final UserService userService;

    public List<Relationship> getUsersRelations(String username) {
        return relationshipRepository.findAll().stream()
                .filter(relationship -> relationship.getUserWhoSaidHelloFirst().getUsername().equals(username) ||
                        (relationship.getUserWhoSaidHelloSecond().getUsername().equals(username) && relationship.getStatus().equals(RelationStatus.BOTH_SAID_HELLO)))
                .collect(Collectors.toList());
    }

    public void deleteUserWithRelations(String username){
        relationshipRepository.deleteAll(getUsersRelations(username));
        userService.deleteAccount(username);
    }

    public Relationship getUserRelationWithUser(String loggedUserUsername, String username) {
        return relationshipRepository.findAll().stream()
                .filter(relationship -> relationship.getUserWhoSaidHelloFirst().getUsername().equals(loggedUserUsername) && relationship.getUserWhoSaidHelloSecond().getUsername().equals(username) ||
                        (relationship.getUserWhoSaidHelloSecond().getUsername().equals(loggedUserUsername) && relationship.getUserWhoSaidHelloFirst().getUsername().equals(username)))
                .findFirst()
                .orElse(null);

    }

    public RelationStatus sayHello(String username, Long id) {
        Optional<Relationship> relationshipOptional = relationshipRepository.findAll().stream()
                .filter(relationship -> (relationship.getUserWhoSaidHelloFirst().getUsername().equals(username)
                        && relationship.getUserWhoSaidHelloSecond().getId().equals(id)) ||
                        relationship.getUserWhoSaidHelloSecond().getUsername().equals(username))
                .findFirst();
        Relationship relationship;
        if (relationshipOptional.isPresent()) {
            relationship = relationshipOptional.get();
            relationship.setUserWhoSaidHelloFirst(userService.getUserByUsername(username));
            relationship.setUserWhoSaidHelloSecond(userService.getUserById(id));
            relationship.setStatus(RelationStatus.BOTH_SAID_HELLO);
        } else {
            relationship = Relationship.builder()
                    .userWhoSaidHelloFirst(userService.getUserByUsername(username))
                    .userWhoSaidHelloSecond(userService.getUserById(id))
                    .status(RelationStatus.USER_SAID_HELLO)
                    .build();
        }
        relationshipRepository.save(relationship);
        return relationship.getStatus();
    }

    public void revertHello(String username, Long id) {
        Optional<Relationship> relationshipOptional = relationshipRepository.findAll().stream()
                .filter(relationship -> (relationship.getUserWhoSaidHelloFirst().getUsername().equals(username)
                        && relationship.getUserWhoSaidHelloSecond().getId().equals(id)) ||
                        relationship.getUserWhoSaidHelloSecond().getUsername().equals(username))
                .findFirst();
        if (relationshipOptional.isPresent()) {
            Relationship relationship = relationshipOptional.get();
            if(relationship.getStatus().equals(RelationStatus.USER_SAID_HELLO)){
                relationshipRepository.delete(relationship);
            } else {
                if (relationship.getUserWhoSaidHelloFirst().getUsername().equals(username)) {
                    User temp = relationship.getUserWhoSaidHelloFirst();
                    relationship.setUserWhoSaidHelloFirst(relationship.getUserWhoSaidHelloSecond());
                    relationship.setUserWhoSaidHelloSecond(temp);
                }
                relationship.setStatus(RelationStatus.USER_SAID_HELLO);
                relationshipRepository.save(relationship);
            }
        }

    }
}
