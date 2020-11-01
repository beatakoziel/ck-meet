package com.meet.ck.controllers.converters;

import com.meet.ck.controllers.requests.UserRequest;
import com.meet.ck.controllers.response.UserResponse;
import com.meet.ck.database.entities.User;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

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

    public static UserResponse entityToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .contactData(ContactDataConverter.entityToResponse(user.getContactData()))
                .age((short) Period.between(user.getDateOfBirth(), LocalDate.now()).getYears())
                .description(user.getDescription())
                .gender(user.getGender())
                .interests(user.getInterests())
                .nickname(user.getNickname())
                .preferredAgeToMeetFrom(user.getPreferredAgeToMeetFrom())
                .preferredAgeToMeetTo(user.getPreferredAgeToMeetTo())
                .preferredGenderToMeet(user.getPreferredGenderToMeet())
                .avatarBytes(user.getAvatar() == null ? null : user.getAvatar().getData())
                .build();
    }

    public static User requestToUpdate(Long userId, UserRequest userRequest) {
        User user = requestToEntity(userRequest);
        user.setId(userId);
        return user;
    }
}
