package com.meet.ck.controllers;

import com.meet.ck.database.enums.Interest;
import com.meet.ck.database.enums.MeetingCategory;
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
@RequestMapping("/info")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class InfoController {

    @GetMapping("/interests")
    public ResponseEntity<List<EnumResponse>> getAvailableInterests() {
        List<EnumResponse> list = Arrays.stream(Interest.values())
                .map(i -> new EnumResponse(i.toString(), i.getKey(), i.getIconName()))
                .collect(Collectors.toList());
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<EnumResponse>> getAvailableCategories() {
        List<EnumResponse> list = Arrays.stream(MeetingCategory.values())
                .map(i -> new EnumResponse(i.toString(), i.getKey(), null))
                .collect(Collectors.toList());
        return new ResponseEntity(list, HttpStatus.OK);
    }
}
