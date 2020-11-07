package com.meet.ck.services;

import com.meet.ck.database.entities.User;

import java.util.List;

public interface IUserService {
    void registerUser(User user);

    void addUserData(User user);

    User getUserById(Long id);

    List<User> getUsersList();

    void deleteUser(Long id);

    void updateUser(User user);

    void updateUserEnabled(Long id, boolean enabled);

}
