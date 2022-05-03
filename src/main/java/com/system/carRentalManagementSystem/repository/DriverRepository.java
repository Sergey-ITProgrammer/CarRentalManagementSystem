package com.system.carRentalManagementSystem.repository;

import com.system.carRentalManagementSystem.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Driver findByBookingId(Long id);
}
