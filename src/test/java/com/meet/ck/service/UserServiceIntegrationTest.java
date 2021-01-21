package com.meet.ck.service;

import com.meet.ck.database.entity.User;
import com.meet.ck.database.enums.Gender;
import com.meet.ck.database.enums.RegistrationStatus;
import com.meet.ck.service.UserService;
import com.meet.ck.utility.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserService sut;

    @Test
    void getUserByUsernameSuccessTest() {
        //given
        String username = "justyna";
        User expectedUser = getTestUser();
        //when
        User user = sut.getUserByUsername(username);
        //then
        Assertions.assertAll(
                () -> assertEquals(expectedUser.getUsername(), user.getUsername()),
                () -> assertEquals(expectedUser.getDescription(), user.getDescription()),
                () -> assertEquals(expectedUser.getGender(), user.getGender()),
                () -> assertEquals(expectedUser.getStatus(), user.getStatus())
        );
    }

    private User getTestUser() {
        return User.builder()
                .username("justyna")
                .description("Chętnie wybiorę się do kina lub kawiarni, żeby spędzić czas w miłym towarzystwie.")
                .gender(Gender.FEMALE)
                .status(RegistrationStatus.COMPLETED)
                .preferredAgeToMeetFrom((short)30)
                .preferredAgeToMeetTo((short)60)
                .build();
    }

    @Test
    void getNotExistingUserByUsernameThrowException() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("secret"));
        //given
        String username = "testwrong";
        //when-then
        assertThrows(NotFoundException.class, () -> {
            sut.getUserByUsername(username);
        });
    }
}
