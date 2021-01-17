package com.meet.ck.service;

import com.meet.ck.database.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image store(MultipartFile file, Long userId);

    Image getImageByUserId(Long userId);

    List<Image> getAllImages();
}
