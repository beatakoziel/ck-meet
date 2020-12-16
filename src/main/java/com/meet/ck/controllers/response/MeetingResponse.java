package com.meet.ck.controllers.response;

import com.meet.ck.controllers.EnumResponse;
import com.meet.ck.database.entities.Comment;
import com.meet.ck.database.entities.Image;
import com.meet.ck.database.entities.User;
import com.meet.ck.database.enums.Gender;
import com.meet.ck.database.enums.Interest;
import com.meet.ck.database.enums.MeetingCategory;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeetingResponse {
    private Long id;
    private String name;
    private String description;
    private  byte[] cover;
    private String date;
    private int maxNumOfParticipants;
    private MeetingParticipantResponse host;
    private List<MeetingParticipantResponse> participants;
    private EnumResponse category;
    private List<CommentResponse> comments;
}
