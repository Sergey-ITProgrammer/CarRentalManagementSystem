package com.system.carRentalManagementSystem.service;

import com.system.carRentalManagementSystem.exception.EntityNotPresentException;
import com.system.carRentalManagementSystem.exception.NullDataException;
import com.system.carRentalManagementSystem.model.Vehicle;
import com.system.carRentalManagementSystem.repository.VehicleRepository;
import lombok.SneakyThrows;
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

    @SneakyThrows
    public Vehicle getVehicleById(Long vehicleId) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        if (vehicle.isPresent()) {
            return vehicle.get();
        } else {
            throw new EntityNotPresentException("There is no such vehicle!");
        }
    }

    public List<Vehicle> getAvailableVehicles() {
        return vehicleRepository.findByAvailability(true);
    }

    public List<Vehicle> getUnavailableVehicles() {
        return vehicleRepository.findByAvailability(false);
    }

    @SneakyThrows
    public Vehicle createVehicle(Vehicle vehicle) {
        if (vehicle.getRegistrationNo() == null || vehicle.getModel() == null || vehicle.getAvailability() == null) {
            throw new NullDataException("Registration number, model or availability cannot be empty!");
        }

        return vehicleRepository.save(vehicle);
    }

    @SneakyThrows
    public Vehicle updateAvailabilityById(Long vehicleId, boolean available) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        if (vehicle.isPresent()) {
            vehicle.get().setAvailability(available);

            return vehicle.get();
        } else {
            throw new EntityNotPresentException("There is no such vehicle!");
        }
    }

    @SneakyThrows
    public Vehicle updateVehicle(Vehicle newVehicle, Long vehicleId) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        if (vehicle.isPresent()) {
            vehicle.get().setRegistrationNo(newVehicle.getRegistrationNo());
            vehicle.get().setModel(newVehicle.getModel());

            return vehicleRepository.save(vehicle.get());
        } else {
            throw new EntityNotPresentException("There is no such vehicle!");
        }
    }

    @SneakyThrows
    public void deleteVehicleById(Long vehicleId) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        if (vehicle.isPresent()) {
            vehicleRepository.deleteById(vehicleId);
        } else {
            throw new EntityNotPresentException("There is no such vehicle!");
        }
    }
}