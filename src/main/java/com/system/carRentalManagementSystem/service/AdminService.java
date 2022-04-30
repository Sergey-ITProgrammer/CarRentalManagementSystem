package com.system.carRentalManagementSystem.service;

import com.system.carRentalManagementSystem.model.Admin;
import com.system.carRentalManagementSystem.model.Driver;
import com.system.carRentalManagementSystem.repository.AdminRepository;
import com.system.carRentalManagementSystem.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin getAdminById(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);

        if (admin.isPresent()) {
            return admin.get();
        }

        return null;
    }

    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public void deleteAdminById(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);

        if(admin.isPresent()) {
            adminRepository.deleteById(id);
        }
    }

    public Admin updateAdmin(Admin newAdmin, Long id) {
        return adminRepository.findById(id).map(a -> {
            a.setName(newAdmin.getName());
            a.setEmailAddress(newAdmin.getEmailAddress());
            a.setContactNo(newAdmin.getContactNo());

            return adminRepository.save(a);
        }).orElseGet(() -> {
            newAdmin.setId(id);

            return adminRepository.save(newAdmin);
        });
    }
}
