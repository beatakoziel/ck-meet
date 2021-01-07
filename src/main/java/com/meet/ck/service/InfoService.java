package com.meet.ck.service;

import com.meet.ck.database.enums.Interest;
import com.meet.ck.database.enums.MeetingCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InfoService {
    public List<Interest> getAvailableInterests() {
        return Arrays.asList(Interest.values());
    }

    public List<MeetingCategory> getAvailableCategories() {
        return Arrays.asList(MeetingCategory.values());
    }
}
