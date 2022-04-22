package com.system.carrentalmanagementsystem.service;

import com.system.carrentalmanagementsystem.model.Role;
import com.system.carrentalmanagementsystem.model.User;
import com.system.carrentalmanagementsystem.repository.UserRepository;
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

    public User addUser(User user) {
        user.setRole(Role.USER);

        return userRepository.save(user);
    }
    public User addDriver(User user) {
        user.setRole(Role.DRIVER);

        return userRepository.save(user);
    }
    public User addEmployee(User user) {
        user.setRole(Role.EMPLOYEE);

        return userRepository.save(user);
    }
    public User addAdmin(User user) {
        user.setRole(Role.ADMIN);

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsers() {
        return userRepository.findUserByRoleUSER();
    }

    public List<User> getAdmins() {
        return userRepository.findUserByRoleADMIN();
    }

    public List<User> getEmployees() {
        return userRepository.findUserByRoleEMPLOYEE();
    }

    public List<User> getDrivers() {
        return userRepository.findUserByRoleDRIVER();
    }

    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    public void deleteUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            userRepository.deleteById(userId);
        }
    }
}
