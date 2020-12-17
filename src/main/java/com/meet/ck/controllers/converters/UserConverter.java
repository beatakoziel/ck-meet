package com.meet.ck.controllers.converters;

import com.meet.ck.controllers.EnumResponse;
import com.meet.ck.controllers.requests.AuthRequest;
import com.meet.ck.controllers.requests.PersonalDataRequest;
import com.meet.ck.controllers.requests.PersonalizationDataRequest;
import com.meet.ck.controllers.response.MeetingParticipantResponse;
import com.meet.ck.controllers.response.UserResponse;
import com.meet.ck.database.entities.Relationship;
import com.meet.ck.database.entities.User;
import com.meet.ck.database.enums.RegistrationStatus;
import com.meet.ck.database.enums.RelationStatus;
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
        System.out.println(user.getDateOfBirth().getYear());
        return UserResponse.builder()
                .id(user.getId())
                .contactData(ContactDataConverter.entityToResponse(user.getContactData()))
                .dateOfBirth(user.getDateOfBirth().getYear() + "-" + user.getDateOfBirth().getMonthValue() + "-" + user.getDateOfBirth().getDayOfMonth())
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
