package com.system.carRentalManagementSystem.controller;

import com.system.carRentalManagementSystem.model.Admin;
import com.system.carRentalManagementSystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }

    @GetMapping("")
    public List<Admin> getAdmins() {
        return adminService.getAdmins();
    }

    @PostMapping("")
    public void createAdmin(@RequestBody Admin admin) {
        adminService.createAdmin(admin);
    }

    @PutMapping("/{id}")
    public void updateAdmin(@RequestBody Admin admin, @PathVariable Long id) {
        adminService.updateAdmin(admin, id);
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable("id") Long id) {
        adminService.deleteAdminById(id);
    }
}
