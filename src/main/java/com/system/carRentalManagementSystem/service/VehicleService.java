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

    public Vehicle getVehicleById(Long vehicleId) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        if (vehicle.isPresent()) {
            return vehicle.get();
        }

        return null;
    }

    public List<Vehicle> getAvailableVehicles() {
        return vehicleRepository.findByAvailability(true);
    }

    public List<Vehicle> getUnavailableVehicles() {
        return vehicleRepository.findByAvailability(false);
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateAvailabilityById(Long vehicleId, boolean available) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        if (vehicle.isPresent()) {
            vehicle.get().setAvailability(available);

            return vehicle.get();
        }

        return null;
    }

    public Vehicle updateVehicle(Vehicle newVehicle, Long vehicleId) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        if (vehicle.isPresent()) {
            vehicle.get().setRegistrationNo(newVehicle.getRegistrationNo());
            vehicle.get().setModelNo(newVehicle.getModelNo());

            return vehicleRepository.save(vehicle.get());
        }

        return null;
    }

    public void deleteVehicleById(Long vehicleId) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        if (vehicle.isPresent()) {
            vehicleRepository.deleteById(vehicleId);
        }
    }
}