package com.system.carRentalManagementSystem.service;

import com.system.carRentalManagementSystem.exception.EntityNotPresentException;
import com.system.carRentalManagementSystem.exception.NullDataException;
import com.system.carRentalManagementSystem.model.User;
import com.system.carRentalManagementSystem.repository.UserRepository;
import lombok.SneakyThrows;
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

    @SneakyThrows
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new EntityNotPresentException("There is no such user!");
        }
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @SneakyThrows
    public User createUser(User user) {
        if (user.getName() == null || user.getEmailAddress() == null || user.getDOB() == null) {
            throw new NullDataException("Name, email or DOB cannot be empty!");
        }

        return userRepository.save(user);
    }

    @SneakyThrows
    public void deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new EntityNotPresentException("There is no such user!");
        }
    }

    @SneakyThrows
    public User updateUser(User newUser, Long id) {
        return userRepository.findById(id).map(u -> {
            u.setName(newUser.getName());
            u.setEmailAddress(newUser.getEmailAddress());
            u.setDOB(newUser.getDOB());

            return userRepository.save(u);
        }).orElseThrow(() -> new EntityNotPresentException("There is no such user!"));
    }
}
