package com.meet.ck.services;

import com.meet.ck.controllers.requests.PersonalDataRequest;
import com.meet.ck.controllers.requests.PersonalizationDataRequest;
import com.meet.ck.database.entities.User;
import com.meet.ck.database.enums.Interest;
import com.meet.ck.database.enums.RegistrationStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUserService {
    void registerUser(User user);

    void registerUserPersonalData(String usernameFromAuthentication, PersonalDataRequest request);

    void registerUserPersonalizationData(String usernameFromAuthentication,
                                         PersonalizationDataRequest request);

    User getUserByUsername(String username);

    void addUserData(User user);

    User getUserById(Long id);

    RegistrationStatus getUserStatus(String username);

    List<User> getUsersList();

    void deleteUser(Long id);

    void updateUser(User user);

    void updateUserEnabled(Long id, boolean enabled);

    void uploadImage(String username, MultipartFile file);
}
