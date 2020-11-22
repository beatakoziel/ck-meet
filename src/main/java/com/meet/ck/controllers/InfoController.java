package com.meet.ck.controllers;

import com.meet.ck.database.enums.Interest;
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

    @GetMapping
    public ResponseEntity<List<InterestResponse>> getAvailableInterests() {
        List<InterestResponse> list = Arrays.stream(Interest.values())
                .map(i -> new InterestResponse(i.toString(), i.getKey()))
                .collect(Collectors.toList());
        return new ResponseEntity(list, HttpStatus.OK);
    }
}
