package com.system.carrentalmanagementsystem.service;

import com.system.carrentalmanagementsystem.model.Booking;
import com.system.carrentalmanagementsystem.repository.BookingRepository;
import com.system.carrentalmanagementsystem.repository.UserRepository;
import com.system.carrentalmanagementsystem.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void addBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    public void deleteBooking(Long bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);

        if(booking.isPresent()) {
            bookingRepository.deleteById(bookingId);
        }
    }
}
