package com.system.carRentalManagementSystem.service;

import com.system.carRentalManagementSystem.model.Vehicle;
import com.system.carRentalManagementSystem.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long vehicleId) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        if (vehicle.isPresent()) {
            vehicleRepository.deleteById(vehicleId);
        }
    }
}