package com.meet.ck.controllers.converters;

import com.meet.ck.controllers.response.CommentResponse;
import com.meet.ck.database.entities.Comment;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CommentConverter {

    public static CommentResponse entityToResponse(Comment entity) {
        return CommentResponse.builder()
                .id(entity.getId())
                .commentatorId(entity.getCommentator()!=null? entity.getCommentator().getId(): null)
                .content(entity.getContent())
                .build();
    }
}
