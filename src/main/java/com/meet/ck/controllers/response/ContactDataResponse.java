package com.meet.ck.controllers.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDataResponse {
    private String email;
    private String phoneNumber;
    private String linkToFacebookProfile;
}
