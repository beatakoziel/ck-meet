package com.meet.ck.controllers.response;

import com.meet.ck.controllers.EnumResponse;
import com.meet.ck.database.enums.Gender;
import com.meet.ck.database.enums.Interest;
import com.meet.ck.database.enums.RelationStatus;
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
    private List<EnumResponse> interests;
    private Gender gender;
    private List<Gender> preferredGenderToMeet;
    private Short preferredAgeToMeetFrom;
    private Short preferredAgeToMeetTo;
    private byte[] avatarBytes;
    private RelationStatus status;
}
