package com.meet.ck.controller.requests;

import com.meet.ck.database.enums.Gender;
import com.meet.ck.database.enums.Interest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalizationDataRequest {
    @NotEmpty
    private List<Interest> interests;

    @NotEmpty
    private List<Gender> preferredGenderToMeet;

    @NotNull
    @Size(min = 18, max = 100)
    private Short preferredAgeToMeetFrom;

    @NotNull
    @Size(min = 18, max = 100)
    private Short preferredAgeToMeetTo;
}
