package com.meet.ck.controller.converters;

import com.meet.ck.controller.response.CommentResponse;
import com.meet.ck.database.entities.Comment;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CommentConverter {

    public static CommentResponse entityToResponse(Comment entity) {
        return CommentResponse.builder()
                .id(entity.getId())
                .commentator(UserConverter.entityToCommentatorResponse(entity.getCommentator()))
                .content(entity.getContent())
                .build();
    }
}
