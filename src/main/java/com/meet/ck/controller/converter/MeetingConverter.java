package com.meet.ck.controller.converter;

import com.meet.ck.controller.response.EnumResponse;
import com.meet.ck.controller.request.MeetingRequest;
import com.meet.ck.controller.response.MeetingResponse;
import com.meet.ck.database.entity.Meeting;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@NoArgsConstructor
public class MeetingConverter {

    public static Meeting meetingToEntity(MeetingRequest request) {
        return Meeting.builder()
                .name(request.getName())
                .description(request.getDescription())
                .maxNumOfParticipants(request.getMaxNumOfParticipants())
                .date(request.getDate())
                .category(request.getCategory())
                .build();
    }

    public static Meeting updateMeetingToEntity(Long userId, MeetingRequest request) {
        return Meeting.builder()
                .id(userId)
                .name(request.getName())
                .description(request.getDescription())
                .maxNumOfParticipants(request.getMaxNumOfParticipants())
                .date(request.getDate())
                .category(request.getCategory())
                .build();
    }

    public static MeetingResponse meetingEntityToResponse(Meeting entity) {
        return MeetingResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .date(entity.getDate() != null ? entity.getDate().toString() : null)
                .maxNumOfParticipants(entity.getMaxNumOfParticipants())
                .host(UserConverter.entityUserToMeetingParticipant(entity.getHost()))
                .category(entity.getCategory() != null ? new EnumResponse(entity.getCategory().name(), entity.getCategory().getKey(), null) : null)
                .participants(entity.getParticipants()
                        .stream()
                        .map(UserConverter::entityUserToMeetingParticipant)
                        .collect(Collectors.toList()))
                .comments(entity.getComments()
                        .stream()
                        .map(CommentConverter::entityToResponse)
                        .collect(Collectors.toList()))
                .build();
    }


}
