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
        return bookingRepository.findByUserId(userId);
    }

    public Booking getUserBookingById(Long userId, Long bookingId) {

        return null;
    }

    public Booking createBookingWithoutDriver(Booking booking, Long userId, Long vehicleId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        if (vehicle.isPresent() && user.isPresent()) {
            booking.setUser(user.get());
            booking.setVehicle(vehicle.get());

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
                booking.setUser(user.get());
                booking.setVehicle(vehicle.get());
                booking.setDriver(driver.get());

                return bookingRepository.save(booking);
            }
        }

        return null;
    }

    public Booking setDriver(Long userId, Long bookingId, Long driverId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        Optional<User> driver = userRepository.findById(driverId);

        if (user.isPresent() && booking.isPresent() && driver.isPresent() && driver.get().getRole().equals(Role.DRIVER)) {
            if (user.get().hasBooking(bookingId)) {
                booking.get().setDriver(driver.get());

                return bookingRepository.save(booking.get());
            }
        }

        return null;
    }

    public void deleteBooking(Long userId, Long bookingId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Booking> booking = bookingRepository.findById(bookingId);

        if(user.isPresent() && booking.isPresent()) {
            if (user.get().hasBooking(bookingId)) {
                bookingRepository.deleteById(bookingId);
            }
        }
    }
}