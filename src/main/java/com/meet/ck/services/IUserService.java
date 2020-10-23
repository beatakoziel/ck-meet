package com.meet.ck.services;

import com.meet.ck.database.entities.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    void createUser(User user);

    User getUserById(Long id);

    List<User> getUsersList();

    void deleteUser(Long id);

    void updateUser(User user);

    void updateUserEnabled(Long id, boolean enabled);

}
