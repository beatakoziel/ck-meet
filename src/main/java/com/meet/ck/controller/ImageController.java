package com.meet.ck.controller;

import com.meet.ck.database.entities.Image;
import com.meet.ck.service.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ImageController {

    private final IImageService imageService;

    @PostMapping(value = "/{userId}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadImage(@PathVariable Long userId, @RequestPart("image") MultipartFile image) {
        imageService.store(image, userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/images")
    public ResponseEntity<Image> getUserById(@PathVariable Long userId) {
        return new ResponseEntity<>(imageService.getImageByUserId(userId), HttpStatus.OK);
    }
}
