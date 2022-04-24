package com.system.carRentalManagementSystem.service;

import com.system.carRentalManagementSystem.role.Role;
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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsers() {
        return userRepository.findUserByRoleUSER();
    }

    public List<User> getDrivers() {
        return userRepository.findUserByRoleDRIVER();
    }

    public List<User> getEmployees() {
        return userRepository.findUserByRoleEMPLOYEE();
    }

    public List<User> getAdmins() {
        return userRepository.findUserByRoleADMIN();
    }

    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    public User createUser(User user) {
        user.setRole(Role.USER);

        return userRepository.save(user);
    }
    public User createDriver(User user) {
        user.setRole(Role.DRIVER);

        return userRepository.save(user);
    }
    public User createEmployee(User user) {
        user.setRole(Role.EMPLOYEE);

        return userRepository.save(user);
    }
    public User createAdmin(User user) {
        user.setRole(Role.ADMIN);

        return userRepository.save(user);
    }

    public void deleteUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            userRepository.deleteById(userId);
        }
    }

    public User updateUser(User newUser, Long id) {
        return userRepository.findById(id).map(u -> {
            u.setName(newUser.getName());
            u.setEmailAddress(newUser.getEmailAddress());
            u.setDOB(newUser.getDOB());
            u.setDriverLicence(newUser.getDriverLicence());

            return userRepository.save(u);
        }).orElseGet(() -> userRepository.save(newUser));
    }
}
