package com.system.carRentalManagementSystem.controller;

import com.system.carRentalManagementSystem.model.User;
import com.system.carRentalManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/drivers/{id}")
    public User getDriverById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/employees/{id}")
    public User getEmployeeById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/admins/{id}")
    public User getAdminById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/drivers")
    public List<User> getDrivers() {
        return userService.getDrivers();
    }

    @GetMapping("/employees")
    public List<User> getEmployees() {
        return userService.getEmployees();
    }

    @GetMapping("/admins")
    public List<User> getAdmins() {
        return userService.getAdmins();
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @PostMapping("/drivers")
    public void createDriver(@RequestBody User user) {
        userService.createDriver(user);
    }

    @PostMapping("/employees")
    public void createEmployee(@RequestBody User user) {
        userService.createEmployee(user);
    }

    @PostMapping("/admins")
    public void createAdmin(@RequestBody User user) {
        userService.createAdmin(user);
    }

    @DeleteMapping("/users")
    public void deleteUserById(@RequestParam("id") Long id) {
        userService.deleteUserById(id);
    }

    @DeleteMapping("/drivers")
    public void deleteDriverById(@RequestParam("id") Long id) {
        userService.deleteUserById(id);
    }

    @DeleteMapping("/employees")
    public void deleteEmployeeById(@RequestParam("id") Long id) {
        userService.deleteUserById(id);
    }

    @DeleteMapping("/admins")
    public void deleteAdminById(@RequestParam("id") Long id) {
        userService.deleteUserById(id);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@RequestBody User user, @PathVariable Long id) {
        userService.updateUser(user, id);
    }

    @PutMapping("/drivers/{id}")
    public void updateDriver(@RequestBody User user, @PathVariable Long id) {
        userService.updateUser(user, id);
    }

    @PutMapping("/employees/{id}")
    public void updateEmployee(@RequestBody User user, @PathVariable Long id) {
        userService.updateUser(user, id);
    }

    @PutMapping("/admins/{id}")
    public void updateAdmin(@RequestBody User user, @PathVariable Long id) {
        userService.updateUser(user, id);
    }
}