package com.meet.ck.services;

import com.meet.ck.database.entities.User;
import com.meet.ck.database.repositories.IContactDataRepository;
import com.meet.ck.database.repositories.IUserRepository;
import com.meet.ck.utilities.AlreadyExistsException;
import com.meet.ck.utilities.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService, IUserService {

    private final IUserRepository userRepository;
    private final IContactDataRepository contactDataRepository;

    @Override
    public void registerUser(User user) {
        userRepository.findByUsername(user.getUsername())
                .ifPresent(existingUser -> {
                    throw new AlreadyExistsException(String.format("User with username %s already exists", user.getUsername()));
                });
        userRepository.save(user);
    }

    @Override
    public void addUserData(User user) {
        contactDataRepository.save(user.getContactData());
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found", id)));
    }

    @Override
    public List<User> getUsersList() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        contactDataRepository.delete(user.getContactData());
        userRepository.delete(user);
    }

    @Override
    public void updateUser(User user) {
        Long contactDataId = getUserById(user.getId()).getContactData().getId();
        user.getContactData().setId(contactDataId);
        contactDataRepository.save(user.getContactData());
        userRepository.save(user);
    }

    @Override
    public void updateUserEnabled(Long id, boolean enabled) {
        User user = getUserById(id);
        user.setEnabled(enabled);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(String.format("User with username %s not found", username)));
    }
}
