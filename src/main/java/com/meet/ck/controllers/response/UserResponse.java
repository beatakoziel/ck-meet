package com.meet.ck.controllers.response;

import com.meet.ck.database.entities.Image;
import com.meet.ck.database.enums.Gender;
import com.meet.ck.database.enums.Interests;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String nickname;
    private String username;
    private Short age;
    private String description;
    private ContactDataResponse contactData;
    private List<Interests> interests;
    private Gender gender;
    private List<Gender> preferredGenderToMeet;
    private Short preferredAgeToMeetFrom;
    private Short preferredAgeToMeetTo;
    private byte[] avatarBytes;
}