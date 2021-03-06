package com.meet.ck.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDataRequest {
    private String email;
    private String phoneNumber;
    private String linkToFacebookProfile;
}
