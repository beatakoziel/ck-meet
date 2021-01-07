package com.meet.ck;

import com.meet.ck.controller.requests.AuthRequest;
import com.meet.ck.database.entities.User;
import com.meet.ck.database.enums.Gender;
import com.meet.ck.database.enums.RegistrationStatus;
import com.meet.ck.service.AuthService;
import com.meet.ck.utility.IncorrectCredentialsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class AuthServiceUnitTest {

    @Autowired
    private AuthService sut;
    @MockBean
    private AuthenticationManager authenticationManager;

    @Test
    void loginUserThrowIncorrectCredentialsTest() {
        //given
        when(authenticationManager.authenticate(any()))
                .thenThrow(new BadCredentialsException("Incorrect username or password"));
        AuthRequest request = getAuthRequest();
        //when-then
        assertThrows(IncorrectCredentialsException.class, () -> {
            sut.basicLogin(request);
        });
    }

    private AuthRequest getAuthRequest() {
        return AuthRequest.builder()
                .username("anna")
                .password("anna")
                .build();
    }

    private User getTestUser() {
        return User.builder()
                .username("justyna")
                .description("Chętnie wybiorę się do kina lub kawiarni, żeby spędzić czas w miłym towarzystwie.")
                .gender(Gender.FEMALE)
                .status(RegistrationStatus.COMPLETED)
                .preferredAgeToMeetFrom((short) 30)
                .preferredAgeToMeetTo((short) 60)
                .build();
    }

    @Test
    void getNotExistingUserByUsernameThrowException() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("secret"));
        //given
        String username = "test";
        //when-then

    }
}
