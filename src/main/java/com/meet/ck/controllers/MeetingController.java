package com.meet.ck.controllers;

import com.meet.ck.controllers.converters.MeetingConverter;
import com.meet.ck.controllers.response.MeetingResponse;
import com.meet.ck.database.entities.Meeting;
import com.meet.ck.database.enums.Interest;
import com.meet.ck.services.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/meetings")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class MeetingController {

    private final MeetingService meetingService;

    @GetMapping
    public ResponseEntity<List<MeetingResponse>> getAvailableInterests() {
        return new ResponseEntity(meetingService.getMeetings()
                .stream()
                .map(MeetingConverter::meetingEntityToResponse)
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}
