package com.meet.ck.service;

import com.meet.ck.controller.request.PersonalDataRequest;
import com.meet.ck.controller.request.PersonalizationDataRequest;
import com.meet.ck.database.entity.Image;
import com.meet.ck.database.entity.User;
import com.meet.ck.database.enums.RegistrationStatus;
import com.meet.ck.database.repository.IImageRepository;
import com.meet.ck.database.repository.IUserRepository;
import com.meet.ck.utility.AlreadyExistsException;
import com.meet.ck.utility.ImageUploadException;
import com.meet.ck.utility.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService, IUserService {

    private final IUserRepository userRepository;
    private final IImageRepository imageRepository;

    @Override
    public void registerUser(User user) {
        userRepository.findByUsername(user.getUsername())
                .ifPresent(existingUser -> {
                    throw new AlreadyExistsException(String.format("User with username %s already exists", user.getUsername()));
                });
        userRepository.save(user);
    }

    @Override
    public void registerUserPersonalData(String username, PersonalDataRequest request) {
        User user = getUserByUsername(username);
        user.setDateOfBirth(request.getDateOfBirth());
        user.setDescription(request.getDescription());
        user.setNickname(request.getNickname());
        user.getContactData().setEmail(request.getEmail());
        user.getContactData().setLinkToFacebookProfile(request.getLinkToFacebookProfile());
        user.getContactData().setPhoneNumber(request.getPhoneNumber());
        user.setGender(request.getGender());
        if (user.getStatus() == RegistrationStatus.NOT_COMPLETED)
            user.setStatus(RegistrationStatus.PERSONAL_DATA);
        userRepository.save(user);
    }

    @Override
    public void registerUserPersonalizationData(String username, PersonalizationDataRequest request) {
        User user = getUserByUsername(username);
        user.setInterests(request.getInterests());
        user.setPreferredGenderToMeet(request.getPreferredGenderToMeet());
        user.setPreferredAgeToMeetFrom(request.getPreferredAgeToMeetFrom());
        user.setPreferredAgeToMeetTo(request.getPreferredAgeToMeetTo());
        if (user.getStatus() == RegistrationStatus.PERSONAL_DATA)
            user.setStatus(RegistrationStatus.PERSONALIZATION);
        userRepository.save(user);
    }

    @Override
    public void deleteAccount(String username) {
        User user = getUserByUsername(username);
        userRepository.delete(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found", id)));
    }

    @Override
    public RegistrationStatus getUserStatus(String username) {
        return getUserByUsername(username).getStatus();
    }

    @Override
    public List<User> getUsersList() {
        return userRepository.findAllByNicknameNotNull();
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    @Override
    public void updateUserEnabled(Long id, boolean enabled) {
        User user = getUserById(id);
        user.setEnabled(enabled);
        userRepository.save(user);
    }

    @Override
    public void uploadImage(String username, MultipartFile file) {
        User user = getUserByUsername(username);
        Image image;
        try {
            image = Image.builder()
                    .name(file.getName())
                    .data(file.getBytes())
                    .type(file.getContentType())
                    .build();
        } catch (IOException e) {
            throw new ImageUploadException();
        }
        image = imageRepository.save(image);
        user.setAvatar(image);
        if (user.getStatus() == RegistrationStatus.PERSONALIZATION)
            user.setStatus(RegistrationStatus.COMPLETED);
        userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(String.format("User with username %s not found", username)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(String.format("User with username %s not found", username)));
    }

    @Override
    public String getUsernameFromAuth(Authentication authentication) {
        return ((User) authentication.getPrincipal()).getUsername();
    }
}
