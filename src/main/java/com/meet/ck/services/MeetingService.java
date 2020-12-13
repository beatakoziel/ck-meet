package com.meet.ck.services;

import com.meet.ck.database.entities.Meeting;
import com.meet.ck.database.entities.User;
import com.meet.ck.database.repositories.IMeetingRepository;
import com.meet.ck.utilities.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingService {
    private final IMeetingRepository meetingRepository;
    private final UserService userService;

    public List<Meeting> getMeetings() {
        return meetingRepository.findAll();
    }

    public void addMeeting(String usernameFromAuth, Meeting meeting) {
        meeting.setHost(userService.getUserByUsername(usernameFromAuth));
        meetingRepository.save(meeting);
    }

    public Meeting findMeetingById(Long meetingId) {
        return meetingRepository.findById(meetingId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Meeting with id %s not found", meetingId)));
    }

    public void editMeeting(Long meetingId, Meeting meetingEditData) {
        Meeting meeting = findMeetingById(meetingId);
        meeting.setName(meetingEditData.getName());
        meeting.setDescription(meetingEditData.getDescription());
        meeting.setCategory(meetingEditData.getCategory());
        meeting.setDate(meetingEditData.getDate());
        meeting.setMaxNumOfParticipants(meetingEditData.getMaxNumOfParticipants());
        meetingRepository.save(meeting);
    }

    public void removeMeeting(Long meetingId) {
        Meeting meeting = findMeetingById(meetingId);
        meetingRepository.delete(meeting);
    }

    public Meeting participateInMeeting(Long meetingId, String username) {
        User user = userService.getUserByUsername(username);
        Meeting meeting = findMeetingById(meetingId);
        meeting.getParticipants().add(user);
        meetingRepository.save(meeting);
        return meeting;
    }

    public Meeting cancelParticipationInMeeting(Long meetingId, String username) {
        Meeting meeting = findMeetingById(meetingId);
        meeting.getParticipants().removeIf((user -> user.getUsername().equals(username)));
        meetingRepository.save(meeting);
        return meeting;
    }
}
