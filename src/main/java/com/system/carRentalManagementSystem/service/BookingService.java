package com.system.carRentalManagementSystem.service;

import com.system.carRentalManagementSystem.model.Booking;
import com.system.carRentalManagementSystem.model.Driver;
import com.system.carRentalManagementSystem.model.User;
import com.system.carRentalManagementSystem.model.Vehicle;
import com.system.carRentalManagementSystem.repository.BookingRepository;
import com.system.carRentalManagementSystem.repository.DriverRepository;
import com.system.carRentalManagementSystem.repository.UserRepository;
import com.system.carRentalManagementSystem.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    private final VehicleRepository vehicleRepository;

    private final UserRepository userRepository;

    private final DriverRepository driverRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, VehicleRepository vehicleRepository,
                          UserRepository userRepository, DriverRepository driverRepository) {
        this.bookingRepository = bookingRepository;
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public Booking getUserBookingById(Long userId, Long bookingId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Booking> booking = bookingRepository.findById(bookingId);

        if (user.isPresent() && booking.isPresent()) {
            if (user.get().hasBooking(bookingId)) {
                return booking.get();
            }
        }

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
        if (booking.hasDriver()) {
            Optional<User> user = userRepository.findById(userId);
            Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
            Optional<Driver> driver = driverRepository.findById(driverId);

            if (user.isPresent() && vehicle.isPresent() && driver.isPresent()) {
                booking.setUser(user.get());
                booking.setVehicle(vehicle.get());
                driver.get().setBooking(booking);

                return bookingRepository.save(booking);
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

    public Driver getDriver(Long userId, Long bookingId, Long driverId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        Optional<Driver> driver = driverRepository.findById(driverId);

        if (user.isPresent() && booking.isPresent() && driver.isPresent()) {
            if (booking.get().hasDriver() && user.get().hasBooking(bookingId) && driver.get().getBooking().equals(booking.get())) {
                return driver.get();
            }
        }

        return null;
    }

    public Driver setDriver(Long userId, Long bookingId, Long driverId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        Optional<Driver> driver = driverRepository.findById(driverId);

        if (user.isPresent() && booking.isPresent() && driver.isPresent()) {
            if (user.get().hasBooking(bookingId) && user.get().hasBooking(bookingId)) {
                driver.get().setBooking(booking.get());

                return driverRepository.save(driver.get());
            }
        }

        return null;
    }
}