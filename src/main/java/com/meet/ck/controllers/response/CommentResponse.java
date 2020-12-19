package com.meet.ck.controllers.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private CommentatorResponse commentator;
    private String content;
}
