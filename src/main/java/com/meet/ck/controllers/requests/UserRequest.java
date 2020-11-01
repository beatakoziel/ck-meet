package com.meet.ck.controllers.requests;

import com.meet.ck.database.enums.Gender;
import com.meet.ck.database.enums.Interests;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotNull
    @Size(min = 1, max = 30)
    private String nickname;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    @Size(max = 500)
    private String description;

    @NotNull
    private ContactDataRequest contactData;

    @NotEmpty
    private List<Interests> interests;

    @NotNull
    private Gender gender;

    @NotEmpty
    private List<Gender> preferredGenderToMeet;

    @NotNull
    @Size(min = 18, max = 100)
    private Short preferredAgeToMeetFrom;

    @NotNull
    @Size(min = 18, max = 100)
    private Short preferredAgeToMeetTo;
}
