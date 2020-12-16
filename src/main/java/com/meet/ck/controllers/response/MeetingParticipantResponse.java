package com.meet.ck.controllers.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeetingParticipantResponse {
    private Long id;
    private String nickname;
    private byte[] avatarBytes;
}
