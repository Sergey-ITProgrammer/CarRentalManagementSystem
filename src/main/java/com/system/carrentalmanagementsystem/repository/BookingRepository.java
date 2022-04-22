package com.system.carrentalmanagementsystem.repository;

import com.system.carrentalmanagementsystem.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
