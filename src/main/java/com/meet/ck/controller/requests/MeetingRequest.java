package com.meet.ck.controller.requests;

import com.meet.ck.database.enums.MeetingCategory;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeetingRequest {
    @NotNull
    @Size(min = 1, max = 70)
    private String name;

    @NotNull
    @Size(max = 500)
    private String description;

    private LocalDate date;

    @NotNull
    private int maxNumOfParticipants;

    @NotNull
    private MeetingCategory category;
}
