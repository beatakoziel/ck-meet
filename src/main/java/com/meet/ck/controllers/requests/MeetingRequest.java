package com.meet.ck.controllers.requests;

import com.meet.ck.database.entities.Comment;
import com.meet.ck.database.entities.Image;
import com.meet.ck.database.entities.User;
import com.meet.ck.database.enums.MeetingCategory;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeetingRequest {
    @Size(min = 1, max = 70)
    private String name;

    @Size(max = 500)
    private String description;

    private LocalDate date;

    private int maxNumOfParticipants;

    private MeetingCategory category;
}
