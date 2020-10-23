package com.meet.ck.controllers;

import com.meet.ck.controllers.requests.UserRequest;
import com.meet.ck.database.entities.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserConverter {
    public static User requestToEntity(UserRequest userRequest) {
        return User.builder()
                .contactData(ContactDataConverter.requestToEntity(userRequest.getContactData()))
                .dateOfBirth(userRequest.getDateOfBirth())
                .description(userRequest.getDescription())
                .gender(userRequest.getGender())
                .interests(userRequest.getInterests())
                .nickname(userRequest.getNickname())
                .preferredAgeToMeetFrom(userRequest.getPreferredAgeToMeetFrom())
                .preferredAgeToMeetTo(userRequest.getPreferredAgeToMeetTo())
                .preferredGenderToMeet(userRequest.getPreferredGenderToMeet())
                .enabled(true)
                .build();
    }

    public static User requestToUpdate(Long userId, UserRequest userRequest) {
        User user = requestToEntity(userRequest);
        user.setId(userId);
        return user;
    }
}
