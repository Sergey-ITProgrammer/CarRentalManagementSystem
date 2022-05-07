package com.system.carRentalManagementSystem.service;

import com.system.carRentalManagementSystem.exception.EntityNotPresentException;
import com.system.carRentalManagementSystem.exception.NullDataException;
import com.system.carRentalManagementSystem.model.Driver;
import com.system.carRentalManagementSystem.repository.DriverRepository;
import lombok.SneakyThrows;
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

    @SneakyThrows
    public Driver getDriverById(Long id) {
        Optional<Driver> driver = driverRepository.findById(id);

        if (driver.isPresent()) {
            return driver.get();
        } else {
            throw new EntityNotPresentException("There is no such driver!");
        }
    }

    public List<Driver> getDrivers() {
        return driverRepository.findAll();
    }

    @SneakyThrows
    public Driver createDriver(Driver driver) {
        if (driver.getName() == null && driver.getAge() == null && driver.getEmailAddress() == null
                && driver.getContactNo() == null && driver.getDriverLicence() == null) {
            throw new NullDataException("Name, age, email, contact number or driver licence cannot be empty!");
        }

        return driverRepository.save(driver);
    }

    @SneakyThrows
    public void deleteDriverById(Long id) {
        Optional<Driver> driver = driverRepository.findById(id);

        if(driver.isPresent()) {
            driverRepository.deleteById(id);
        } else {
            throw new EntityNotPresentException("There is no such driver!");
        }
    }

    @SneakyThrows
    public Driver updateDriver(Driver newDriver, Long id) {
        return driverRepository.findById(id).map(d -> {
            d.setName(newDriver.getName());
            d.setEmailAddress(newDriver.getEmailAddress());
            d.setDriverLicence(newDriver.getDriverLicence());
            d.setAge(newDriver.getAge());
            d.setContactNo(newDriver.getContactNo());

            return driverRepository.save(d);
        }).orElseThrow(() -> new EntityNotPresentException("There is no such driver!"));
    }
}
