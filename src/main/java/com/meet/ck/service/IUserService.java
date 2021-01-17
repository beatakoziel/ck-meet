package com.meet.ck.service;

import com.meet.ck.controller.request.PersonalDataRequest;
import com.meet.ck.controller.request.PersonalizationDataRequest;
import com.meet.ck.database.entity.User;
import com.meet.ck.database.enums.RegistrationStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUserService {
    void registerUser(User user);

    void registerUserPersonalData(String usernameFromAuthentication, PersonalDataRequest request);

    void registerUserPersonalizationData(String usernameFromAuthentication,
                                         PersonalizationDataRequest request);

    User getUserByUsername(String username);

    void deleteAccount(String username);

    User getUserById(Long id);

    RegistrationStatus getUserStatus(String username);

    List<User> getUsersList();

    void deleteUser(Long id);

    void updateUserEnabled(Long id, boolean enabled);

    void uploadImage(String username, MultipartFile file);
}
