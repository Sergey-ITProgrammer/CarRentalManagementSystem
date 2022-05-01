package com.system.carRentalManagementSystem.controller;

import com.system.carRentalManagementSystem.model.Vehicle;
import com.system.carRentalManagementSystem.modelAssembler.VehicleModelAssembler;
import com.system.carRentalManagementSystem.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    private final VehicleModelAssembler modelAssembler;

    @Autowired
    public VehicleController(VehicleService vehicleService, VehicleModelAssembler modelAssembler) {
        this.vehicleService = vehicleService;
        this.modelAssembler = modelAssembler;
    }

    @GetMapping("")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{vehicleId}")
    public Vehicle getVehicleById(@PathVariable("vehicleId") Long vehicleId) {
        return vehicleService.getVehicleById(vehicleId);
    }

    @GetMapping("/available")
    public List<Vehicle> getAvailableVehicles() {
        return vehicleService.getAvailableVehicles();
    }

    @GetMapping("/unavailable")
    public List<Vehicle> getUnavailableVehicles() {
        return vehicleService.getUnavailableVehicles();
    }

    @PostMapping("")
    public void createVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.createVehicle(vehicle);
    }

    @PutMapping("/{vehicleId}")
    public void updateVehicle(@RequestBody Vehicle newVehicle, @PathVariable("vehicleId") Long vehicleId) {
        vehicleService.updateVehicle(newVehicle, vehicleId);
    }

    @PatchMapping("/{vehicleId}")
    public void updateAvailability(@PathVariable("vehicleId") Long vehicleId, @RequestParam boolean available) {
        vehicleService.updateAvailabilityById(vehicleId, available);
    }

    @DeleteMapping("/{vehicleId}")
    public void deleteVehicleById(@PathVariable("vehicleId") Long vehicleId) {
        vehicleService.deleteVehicleById(vehicleId);
    }
}
