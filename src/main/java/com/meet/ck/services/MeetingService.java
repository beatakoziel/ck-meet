package com.meet.ck.services;

import com.meet.ck.database.entities.Meeting;
import com.meet.ck.database.repositories.IMeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingService {
    private final IMeetingRepository meetingRepository;

    public List<Meeting> getMeetings(){
        return meetingRepository.findAll();
    }
}
