package com.meet.ck.controllers;

import com.meet.ck.controllers.requests.ContactDataRequest;
import com.meet.ck.database.entities.ContactData;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ContactDataConverter {
    public static ContactData requestToEntity(ContactDataRequest contactDataRequest) {
        return ContactData.builder()
                .email(contactDataRequest.getEmail())
                .linkToFacebookProfile(contactDataRequest.getLinkToFacebookProfile())
                .phoneNumber(contactDataRequest.getPhoneNumber())
                .build();
    }
}
