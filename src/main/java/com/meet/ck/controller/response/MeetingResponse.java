package com.meet.ck.controller.response;

import com.meet.ck.controller.EnumResponse;
import lombok.*;

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
