package com.system.carRentalManagementSystem.service;

import com.system.carRentalManagementSystem.model.Driver;
import com.system.carRentalManagementSystem.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {
    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver getDriverById(Long id) {
        Optional<Driver> driver = driverRepository.findById(id);

        if (driver.isPresent()) {
            return driver.get();
        }

        return null;
    }

    public List<Driver> getDrivers() {
        return driverRepository.findAll();
    }

    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public void deleteDriverById(Long id) {
        Optional<Driver> driver = driverRepository.findById(id);

        if(driver.isPresent()) {
            driverRepository.deleteById(id);
        }
    }

    public Driver updateDriver(Driver newDriver, Long id) {
        return driverRepository.findById(id).map(d -> {
            d.setName(newDriver.getName());
            d.setEmailAddress(newDriver.getEmailAddress());
            d.setDriverLicence(newDriver.getDriverLicence());
            d.setAge(newDriver.getAge());
            d.setContactNo(newDriver.getContactNo());

            return driverRepository.save(d);
        }).orElseGet(() -> {
            newDriver.setId(id);

            return driverRepository.save(newDriver);
        });
    }
}
