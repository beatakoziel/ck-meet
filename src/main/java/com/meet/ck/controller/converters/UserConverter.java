package com.meet.ck.controller.converters;

import com.meet.ck.controller.EnumResponse;
import com.meet.ck.controller.requests.AuthRequest;
import com.meet.ck.controller.requests.PersonalDataRequest;
import com.meet.ck.controller.requests.PersonalizationDataRequest;
import com.meet.ck.controller.response.CommentatorResponse;
import com.meet.ck.controller.response.MeetingParticipantResponse;
import com.meet.ck.controller.response.UserResponse;
import com.meet.ck.database.entities.Relationship;
import com.meet.ck.database.entities.User;
import com.meet.ck.database.enums.RegistrationStatus;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.Period;
import java.util.stream.Collectors;

@NoArgsConstructor
public class UserConverter {

    public static User userAuthToEntity(AuthRequest request) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return User.builder()
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .status(RegistrationStatus.NOT_COMPLETED)
                .enabled(true)
                .build();
    }

    public static MeetingParticipantResponse entityUserToMeetingParticipant(User entity) {
        return MeetingParticipantResponse.builder()
                .id(entity.getId())
                .nickname(entity.getNickname())
                .avatarBytes(entity.getAvatar() != null ? entity.getAvatar().getData() : null)
                .build();
    }

    public static User entityUserToMeetingParticipant(Long userId, PersonalDataRequest request) {
        return User.builder()
                .id(userId)
                .nickname(request.getNickname())
                .dateOfBirth(request.getDateOfBirth())
                .description(request.getDescription())
                .gender(request.getGender())
                .status(RegistrationStatus.PERSONAL_DATA)
                .build();
    }

    public static User personalizationDataToEntity(Long userId, PersonalizationDataRequest request) {
        return User.builder()
                .id(userId)
                .interests(request.getInterests())
                .preferredAgeToMeetFrom(request.getPreferredAgeToMeetFrom())
                .preferredAgeToMeetTo(request.getPreferredAgeToMeetTo())
                .preferredGenderToMeet(request.getPreferredGenderToMeet())
                .status(RegistrationStatus.PERSONALIZATION)
                .build();
    }

    public static UserResponse loggedUserToResponse(User user) {
        String monthOfBirth = "";
        if (user.getDateOfBirth().getMonthValue() < 10)
            monthOfBirth = "0";
        monthOfBirth += user.getDateOfBirth().getMonthValue();
        String dayOfBirth = "";
        if (user.getDateOfBirth().getDayOfMonth() < 10)
            dayOfBirth = "0";
        dayOfBirth += user.getDateOfBirth().getDayOfMonth();

        return UserResponse.builder()
                .id(user.getId())
                .contactData(ContactDataConverter.entityToResponse(user.getContactData()))
                .dateOfBirth(user.getDateOfBirth().getYear() + "-" + monthOfBirth + "-" + dayOfBirth)
                .description(user.getDescription())
                .gender(user.getGender())
                .interests(user.getInterests()
                        .stream()
                        .map(interest -> new EnumResponse(interest.name(), interest.getKey(), interest.getIconName()))
                        .collect(Collectors.toList()))
                .nickname(user.getNickname())
                .username(user.getUsername())
                .preferredAgeToMeetFrom(user.getPreferredAgeToMeetFrom())
                .preferredAgeToMeetTo(user.getPreferredAgeToMeetTo())
                .preferredGenderToMeet(user.getPreferredGenderToMeet())
                .avatarBytes(user.getAvatar() == null ? null : user.getAvatar().getData())
                .build();
    }

    public static CommentatorResponse entityToCommentatorResponse(User user) {
        return CommentatorResponse.builder()
                .id(user.getId())
                .avatarBytes(user.getAvatar() == null ? null : user.getAvatar().getData())
                .nickname(user.getNickname())
                .build();
    }

    public static UserResponse entityToResponse(User user, Relationship relationship) {
        return UserResponse.builder()
                .id(user.getId())
                .contactData(ContactDataConverter.entityToResponse(user.getContactData()))
                .age((short) Period.between(user.getDateOfBirth(), LocalDate.now()).getYears())
                .description(user.getDescription())
                .gender(user.getGender())
                .interests(user.getInterests()
                        .stream()
                        .map(interest -> new EnumResponse(interest.name(), interest.getKey(), interest.getIconName()))
                        .collect(Collectors.toList()))
                .nickname(user.getNickname())
                .username(user.getUsername())
                .preferredAgeToMeetFrom(user.getPreferredAgeToMeetFrom())
                .preferredAgeToMeetTo(user.getPreferredAgeToMeetTo())
                .preferredGenderToMeet(user.getPreferredGenderToMeet())
                .avatarBytes(user.getAvatar() == null ? null : user.getAvatar().getData())
                .status(relationship == null ? null : relationship.getStatus())
                .build();
    }
}
