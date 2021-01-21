package com.meet.ck.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meet.ck.config.jwt.JwtAuthenticationFilter;
import com.meet.ck.config.jwt.JwtUtil;
import com.meet.ck.database.entity.Meeting;
import com.meet.ck.database.entity.User;
import com.meet.ck.database.enums.MeetingCategory;
import com.meet.ck.database.repository.IUserRepository;
import com.meet.ck.service.UserService;
import com.meet.ck.utility.AuthUsernameExtractor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collection;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsersControllerTest {
    @Autowired
    private JwtUtil jwtUtil;
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @MockBean
    private AuthenticationManager authenticationManager;
@Autowired
private IUserRepository userRepository;
    @MockBean
    private AuthUsernameExtractor extractor;
    @Autowired
    private UserService userService;

    @BeforeAll
    public void setUp() {
        when(extractor.getUsernameFromAuth(any())).thenReturn("justyna");
        //when(userService.getUserByUsername(any())).thenReturn(buildUser());
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(getAuthentication());
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(jwtAuthenticationFilter)
                .build();
    }

    @Test
    public void shouldGetUserByIdAndReturnOkStatus() throws Exception {
        this.mockMvc.perform(get("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetUsersAndReturnOkStatus() throws Exception {
        this.mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetCurrentUserAndReturnOkStatus() throws Exception {
        when(extractor.getUsernameFromAuth(any())).thenReturn("justyna");

        this.mockMvc.perform(get("/users/current")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetCurrentUserStatusAndReturnOkStatus() throws Exception {
        when(extractor.getUsernameFromAuth(any())).thenReturn("justyna");

        this.mockMvc.perform(get("/users/status")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Meeting getMeeting(String name) {
        return Meeting.builder()
                .name(name)
                .description("test")
                .category(MeetingCategory.ACTIVITY)
                .maxNumOfParticipants(12)
                .date(null)
                .build();
    }

    public User buildUser() {
        return userRepository.findById(3L).orElse(new User());
    }

    public Authentication getAuthentication() {
        return new Authentication() {
            @Override
            public boolean equals(Object another) {
                return false;
            }

            @Override
            public String toString() {
                return null;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean b) throws IllegalArgumentException {

            }
        };
    }
}
