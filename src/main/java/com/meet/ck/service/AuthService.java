package com.meet.ck.service;

import com.meet.ck.config.jwt.JwtUtil;
import com.meet.ck.controller.request.AuthRequest;
import com.meet.ck.controller.response.AuthResponse;
import com.meet.ck.database.enums.RegistrationStatus;
import com.meet.ck.utility.IncorrectCredentialsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtTokenUtil;

    public AuthResponse basicLogin(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new IncorrectCredentialsException("Incorrect username or password");
        }
        final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        RegistrationStatus status = userService.getUserStatus(request.getUsername());
        return AuthResponse.builder()
                .status(status)
                .token(token)
                .build();
    }

}
