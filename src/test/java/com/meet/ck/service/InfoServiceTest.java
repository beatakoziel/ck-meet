package com.meet.ck.service;

import com.meet.ck.database.enums.Interest;
import com.meet.ck.database.enums.MeetingCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InfoServiceTest {

    @Autowired
    InfoService sut;

    @Test
    void getAvailableInterestsSuccessTest(){
        //given-when
        List<Interest> interests = sut.getAvailableInterests();
        //then
        assertEquals(20, interests.size());
    }

    @Test
    void getAvailableCategoriesSuccessTest(){
        //given-when
        List<MeetingCategory> categories = sut.getAvailableCategories();
        //then
        assertEquals(14, categories.size());
    }
}
