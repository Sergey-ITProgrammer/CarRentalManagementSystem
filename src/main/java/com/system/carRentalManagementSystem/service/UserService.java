package com.system.carRentalManagementSystem.service;

import com.system.carRentalManagementSystem.model.User;
import com.system.carRentalManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        }

        return null;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()) {
            userRepository.deleteById(id);
        }
    }

    public User updateUser(User newUser, Long id) {
        return userRepository.findById(id).map(u -> {
            u.setName(newUser.getName());
            u.setEmailAddress(newUser.getEmailAddress());
            u.setDOB(newUser.getDOB());

            return userRepository.save(u);
        }).orElseGet(() -> null);
    }
}
