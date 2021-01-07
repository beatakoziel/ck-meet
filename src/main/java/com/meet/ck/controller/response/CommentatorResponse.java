package com.meet.ck.controller.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentatorResponse {
    private Long id;
    private String nickname;
    private byte[] avatarBytes;
}
