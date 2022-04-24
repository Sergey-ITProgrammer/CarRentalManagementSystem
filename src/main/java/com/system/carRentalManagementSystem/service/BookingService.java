package com.system.carRentalManagementSystem.service;

import com.system.carRentalManagementSystem.model.Booking;
import com.system.carRentalManagementSystem.model.User;
import com.system.carRentalManagementSystem.model.Vehicle;
import com.system.carRentalManagementSystem.repository.BookingRepository;
import com.system.carRentalManagementSystem.repository.UserRepository;
import com.system.carRentalManagementSystem.repository.VehicleRepository;
import com.system.carRentalManagementSystem.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    private final VehicleRepository vehicleRepository;

    private final UserRepository userRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, VehicleRepository vehicleRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return user.get().getBookings().stream().toList();
        }

        return null;
    }

    public Booking createBookingWithoutDriver(Booking booking, Long userId, Long vehicleId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        if (vehicle.isPresent() && user.isPresent()) {
            booking.setCustomerDetails(user.get());
            booking.setVehicleDetails(vehicle.get());

            return bookingRepository.save(booking);
        }

        return null;
    }

    public Booking createBookingWithDriver(Booking booking, Long userId, Long vehicleId, Long driverId) {
        if (booking.getDriverOptions()) {
            Optional<User> user = userRepository.findById(userId);
            Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
            Optional<User> driver = userRepository.findById(driverId);

            if (user.isPresent() && vehicle.isPresent() && driver.isPresent() && driver.get().getRole().equals(Role.DRIVER)) {
                booking.setCustomerDetails(user.get());
                booking.setVehicleDetails(vehicle.get());
                booking.setDriverDetails(driver.get());

                return bookingRepository.save(booking);
            }
        }

        return null;
    }

    public Booking setDriver(Long bookingId, Long driverId) {
        Optional<User> driver = userRepository.findById(driverId);
        Optional<Booking> booking = bookingRepository.findById(bookingId);

        if (driver.isPresent() && booking.isPresent() && driver.get().getRole().equals(Role.DRIVER)) {
            booking.get().setDriverDetails(driver.get());
            booking.get().setDriverOptions(true);

            return bookingRepository.save(booking.get());
        }

        return null;
    }

    public void deleteBooking(Long bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);

        if(booking.isPresent()) {
            bookingRepository.deleteById(bookingId);
        }
    }
}