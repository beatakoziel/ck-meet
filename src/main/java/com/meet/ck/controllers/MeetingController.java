package com.meet.ck.controllers;

import com.meet.ck.controllers.converters.MeetingConverter;
import com.meet.ck.controllers.requests.MeetingRequest;
import com.meet.ck.controllers.response.MeetingResponse;
import com.meet.ck.database.entities.User;
import com.meet.ck.services.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.meet.ck.controllers.converters.MeetingConverter.meetingToEntity;

@RestController
@RequestMapping("/meetings")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class MeetingController {

    private final MeetingService meetingService;

    @GetMapping
    public ResponseEntity<List<MeetingResponse>> getMeetings() {
        return new ResponseEntity(meetingService.getMeetings()
                .stream()
                .map(MeetingConverter::meetingEntityToResponse)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addMeeting(Authentication authentication, @RequestBody @Valid MeetingRequest request) {
        meetingService.addMeeting(getUsernameFromAuth(authentication), meetingToEntity(request));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/{meetingId}/participate")
    public ResponseEntity<MeetingResponse> participateInMeeting(@PathVariable("meetingId") Long meetingId, Authentication authentication) {
        MeetingResponse meetingResponse =
                MeetingConverter.meetingEntityToResponse(meetingService.participateInMeeting(meetingId, getUsernameFromAuth(authentication)));
        return new ResponseEntity(meetingResponse, HttpStatus.OK);
    }

    @PostMapping("/{meetingId}/cancel")
    public ResponseEntity<MeetingResponse> cancelParticipationInMeeting(@PathVariable("meetingId") Long meetingId, Authentication authentication) {
        MeetingResponse meetingResponse =
                MeetingConverter.meetingEntityToResponse(meetingService.cancelParticipationInMeeting(meetingId, getUsernameFromAuth(authentication)));
        return new ResponseEntity(meetingResponse, HttpStatus.OK);
    }

    @PutMapping("/{meetingId}")
    public ResponseEntity<Void> editMeeting(@PathVariable("meetingId") Long meetingId, @RequestBody MeetingRequest request) {
        meetingService.editMeeting(meetingId, meetingToEntity(request));
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{meetingId}")
    public ResponseEntity<Void> editMeeting(@PathVariable("meetingId") Long meetingId) {
        meetingService.removeMeeting(meetingId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{meetingId}/comment")
    public ResponseEntity<Void> addComment(Authentication authentication, @PathVariable("meetingId") Long meetingId, @RequestBody String content) {
        meetingService.addComment(meetingId, getUsernameFromAuth(authentication), content);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/{meetingId}/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("meetingId") Long meetingId, @PathVariable("commentId") Long commentId) {
        meetingService.deleteComment(meetingId, commentId);
        return new ResponseEntity(HttpStatus.OK);
    }

    private String getUsernameFromAuth(Authentication authentication) {
        return ((User) authentication.getPrincipal()).getUsername();
    }
}
