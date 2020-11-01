package com.meet.ck.services;

import com.meet.ck.database.entities.Image;
import com.meet.ck.database.entities.User;
import com.meet.ck.database.repositories.IImageRepository;
import com.meet.ck.database.repositories.IUserRepository;
import com.meet.ck.utilities.ImageUploadException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService{

    private final IImageRepository imageRepository;
    private final IUserRepository userRepository;
    private final IUserService userService;

    @Override
    public Image store(MultipartFile file, Long userId) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Image image = null;
        try {
            image = Image.builder()
                    .name(fileName)
                    .type(file.getContentType())
                    .data(file.getBytes())
                    .build();
        } catch (IOException e) {
            throw new ImageUploadException();
        }
        Image savedImage = imageRepository.save(image);
        User user = userService.getUserById(userId);
        user.setAvatar(savedImage);
        userRepository.save(user);
        return savedImage;
    }

    @Override
    public Image getImageByUserId(Long userId) {
        return userService.getUserById(userId).getAvatar();
    }

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

}
