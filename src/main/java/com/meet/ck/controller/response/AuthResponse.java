package com.meet.ck.controller.response;

import com.meet.ck.database.enums.RegistrationStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private RegistrationStatus status;
}
