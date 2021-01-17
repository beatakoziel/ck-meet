package com.meet.ck.service;

import com.meet.ck.controller.request.PersonalDataRequest;
import com.meet.ck.controller.request.PersonalizationDataRequest;
import com.meet.ck.database.entity.Relationship;
import com.meet.ck.database.entity.User;
import com.meet.ck.database.enums.RelationStatus;
import com.meet.ck.utility.AlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService sut;

    @Test
    void registerUserAlreadyExistsTest() {
        //given
        User user = getUser("hanna");
        //when-then
        assertThrows(AlreadyExistsException.class, () -> {
            sut.registerUser(user);
        });
    }

    @Test
    void registerUserSuccessTest() {
        //given
        User user = getUser("test");
        //when
        sut.registerUser(user);
        //then
        User addedUser = sut.getUserByUsername("test");
        assertNotNull(addedUser.getUsername());
    }

    @Test
    void getUsersListSuccessTest() {
        //given-when
        List<User> users = sut.getUsersList();
        //then
        assertTrue(users.size() > 0);
    }

    @Test
    void registerUserPersonalDataSuccessTest() {
        PersonalDataRequest request = PersonalDataRequest.builder()
                .nickname("nickname")
                .description("test description")
                .email("test@test.com")
                .build();
        sut.registerUserPersonalData("hanna", request);
        User user = sut.getUserByUsername("hanna");
        assertEquals("nickname", user.getNickname());
        assertEquals("test description", user.getDescription());
        assertEquals("test@test.com", user.getContactData().getEmail());
    }

    @Test
    void registerUserPersonalizationDataSuccessTest() {
        PersonalizationDataRequest request = PersonalizationDataRequest.builder()
                .preferredAgeToMeetFrom((short)50)
                .preferredAgeToMeetTo((short)60)
                .build();
        sut.registerUserPersonalizationData("hanna", request);
        User user = sut.getUserByUsername("hanna");
        assertEquals((short)50, user.getPreferredAgeToMeetFrom());
        assertEquals((short)60, user.getPreferredAgeToMeetTo());
    }

    private User getUser(String username) {
        return User.builder()
                .username(username)
                .password("test")
                .enabled(true)
                .build();
    }
}
