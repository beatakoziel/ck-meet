package com.meet.ck.controller.converter;

import com.meet.ck.controller.request.ContactDataRequest;
import com.meet.ck.controller.response.ContactDataResponse;
import com.meet.ck.database.entity.ContactData;
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

    public static ContactDataResponse entityToResponse(ContactData contactData) {
        return ContactDataResponse.builder()
                .email(contactData.getEmail())
                .phoneNumber(contactData.getPhoneNumber())
                .linkToFacebookProfile(contactData.getLinkToFacebookProfile())
                .build();
    }
}
