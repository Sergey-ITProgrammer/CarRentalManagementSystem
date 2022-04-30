package com.system.carRentalManagementSystem.controller;

import com.system.carRentalManagementSystem.model.Driver;
import com.system.carRentalManagementSystem.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/{id}")
    public Driver getDriverById(@PathVariable Long id) {
        return driverService.getDriverById(id);
    }

    @GetMapping("")
    public List<Driver> getDrivers() {
        return driverService.getDrivers();
    }

    @PostMapping("")
    public void createDriver(@RequestBody Driver driver) {
        driverService.createDriver(driver);
    }

    @PutMapping("/{id}")
    public void updateDriver(@RequestBody Driver driver, @PathVariable Long id) {
        driverService.updateDriver(driver, id);
    }

    @DeleteMapping("/{id}")
    public void deleteDriver(@PathVariable("id") Long id) {
        driverService.deleteDriverById(id);
    }
}
