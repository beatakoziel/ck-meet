package com.meet.ck.service;

import com.meet.ck.database.entity.Relationship;
import com.meet.ck.database.enums.RelationStatus;
import com.meet.ck.database.repository.IRelationshipRepository;
import com.meet.ck.database.repository.IUserRepository;
import com.meet.ck.service.RelationshipService;
import com.meet.ck.service.UserService;
import com.meet.ck.utility.AuthUsernameExtractor;
import com.meet.ck.utility.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class RelationshipServiceTest {

    @Autowired
    RelationshipService sut;

    @Autowired
    UserService userService;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IRelationshipRepository relationshipRepository;

    @Test
    void sayHelloUserSaidHelloSuccessTest() {
        //given-when
        RelationStatus status = sut.sayHello("kasia", 1L);
        //then
        assertEquals(RelationStatus.USER_SAID_HELLO, status);
    }

    @Test
    void sayHelloBothSaidHelloSuccessTest() {
        //given-when
        RelationStatus firstStatus = sut.sayHello("hanna", 11L);
        RelationStatus secondStatus = sut.sayHello("justyna", 10L);
        //then
        assertEquals(RelationStatus.USER_SAID_HELLO, firstStatus);
        assertEquals(RelationStatus.BOTH_SAID_HELLO, secondStatus);
    }

    @Test
    void revertHelloSingleSaidHelloSuccessTest() {
        //given-when
        sut.sayHello("wiktor", 13L);
        RelationStatus secondStatus = sut.sayHello("ola", 12L);
        sut.revertHello("wiktor", 13L);
        RelationStatus afterRevertStatus = sut.sayHello("ola", 12L);
        //then
        assertEquals(RelationStatus.BOTH_SAID_HELLO, secondStatus);
        assertEquals(RelationStatus.USER_SAID_HELLO, afterRevertStatus);
    }

    @Test
    void getUserRelationsSuccessTest() {
        //given-when
        sut.sayHello("jacek", 10L);
        //then
        List<Relationship> relationshipList = sut.getUsersRelations("jacek");
        assertEquals(1, relationshipList.size());
    }

    @Test
    void getUserRelationWithUserSuccessTest() {
        //given-when
        sut.sayHello("jacek", 10L);
        //then
        Relationship relationshipList = sut.getUserRelationWithUser("jacek", "hanna");
        assertEquals(RelationStatus.USER_SAID_HELLO, relationshipList.getStatus());
    }

    @Test
    void deleteUserWithRelationsSuccessTest() {
        //given
        sut.sayHello("ela", 8L);
        //when
        sut.deleteUserWithRelations("ela");
        //then
        assertThrows(NotFoundException.class, () -> {
            userService.getUserByUsername("ela");
        });
    }
}
