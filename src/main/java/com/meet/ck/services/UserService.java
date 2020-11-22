package com.meet.ck.services;

import com.meet.ck.controllers.requests.PersonalDataRequest;
import com.meet.ck.database.entities.ContactData;
import com.meet.ck.database.entities.User;
import com.meet.ck.database.enums.Interest;
import com.meet.ck.database.enums.RegistrationStatus;
import com.meet.ck.database.repositories.IContactDataRepository;
import com.meet.ck.database.repositories.IUserRepository;
import com.meet.ck.utilities.AlreadyExistsException;
import com.meet.ck.utilities.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
        ContactData contactData = new ContactData();
        user.setContactData(contactData);
        contactDataRepository.save(contactData);
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
    public List<Interest> getAvailableInterests() {
        return Arrays.asList(Interest.values());
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
}
