package com.system.carrentalmanagementsystem.repository;

import com.system.carrentalmanagementsystem.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
