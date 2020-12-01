package com.meet.ck.controllers.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.meet.ck.database.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDataRequest {
    @NotNull
    @Size(min = 1, max = 30)
    private String nickname;

    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;

    @NotNull
    @Size(max = 500)
    private String description;

    private String email;
    private String phoneNumber;
    private String linkToFacebookProfile;

    @NotNull
    private Gender gender;
}
