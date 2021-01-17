package com.meet.ck.service;

import com.meet.ck.database.entity.Comment;
import com.meet.ck.database.entity.Meeting;
import com.meet.ck.database.entity.User;
import com.meet.ck.database.enums.MeetingCategory;
import com.meet.ck.utility.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class MeetingServiceTest {

    @Autowired
    private MeetingService sut;

    @Test
    void addNewMeetingTest() {
        //given
        Meeting meeting = getMeeting("test1");
        //when
        sut.addMeeting("hanna", meeting);
        //then
        long count = sut.getMeetings().stream()
                .filter(m -> m.getName().equals("test1"))
                .count();
        assertEquals(1, count);
    }

    @Test
    void findMeetingByIdSuccessTest() {
        //given-when
        Meeting meeting = sut.findMeetingById(1L);
        //then
        assertEquals("Wyjście na kawę", meeting.getName());
        assertEquals("Usłyszałam ostatnio o kawiarnii Bosko w Kielcach. Chętnie wyjdę tam na kawę i jakieś ciacho.", meeting.getDescription());
        assertEquals(MeetingCategory.BOARD_GAMES, meeting.getCategory());
        assertEquals(2, meeting.getMaxNumOfParticipants());
    }

    @Test
    void findMeetingByIdNotFoundTest() {
        //given-when-then
        assertThrows(NotFoundException.class, () -> {
            sut.findMeetingById(13L);
        });
    }

    @Test
    void editMeetingSuccessTest() {
        //given
        Meeting meeting = getMeeting("test2");
        //when
        sut.editMeeting(2L, meeting);
        //then
        Meeting editedMeeting = sut.findMeetingById(2L);
        assertEquals("test2", editedMeeting.getName());
        assertEquals("test", editedMeeting.getDescription());
        assertEquals(MeetingCategory.ACTIVITY, editedMeeting.getCategory());
        assertEquals(12, editedMeeting.getMaxNumOfParticipants());
    }

    @Test
    void participateInMeetingSuccessTest() {
        //given-when
        Meeting meeting = sut.participateInMeeting(2L, "hanna");
        User user = meeting.getParticipants().get(0);
        //then
        assertEquals("hanna", user.getUsername());
    }

    @Test
    void cancelParticipationInMeetingSuccessTest() {
        //given-when
        Meeting meeting = sut.participateInMeeting(1L, "adam");
        Meeting meetingAfterCancelParticipation = sut.cancelParticipationInMeeting(2L, "adam");
        //then
        assertEquals(1,  meeting.getParticipants().size());
        assertEquals(1,  meetingAfterCancelParticipation.getParticipants().size());
    }

    @Test
    void addCommentSuccessTest() {
        //given-when
        List<Comment> comments = sut.addComment(2L, "hanna", "content");
        //then
        assertEquals(1, comments.size());
        assertEquals("content", comments.get(0).getContent());
    }

    @Test
    void deleteCommentSuccessTest() {
        //given-when
        List<Comment> comments = sut.addComment(1L, "hanna", "content");
        List<Comment> commentsAfterDelete = sut.deleteComment(1L, 1L);
        //then
        assertEquals(1, comments.size());
        assertEquals(0, commentsAfterDelete.size());
    }

    private Meeting getMeeting(String name) {
        return Meeting.builder()
                .name(name)
                .description("test")
                .category(MeetingCategory.ACTIVITY)
                .maxNumOfParticipants(12)
                .date(null)
                .build();
    }
}
