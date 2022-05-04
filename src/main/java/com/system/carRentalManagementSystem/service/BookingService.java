package com.system.carRentalManagementSystem.service;

import com.system.carRentalManagementSystem.exception.EntityNotPresentException;
import com.system.carRentalManagementSystem.exception.NullDataException;
import com.system.carRentalManagementSystem.model.Booking;
import com.system.carRentalManagementSystem.model.Driver;
import com.system.carRentalManagementSystem.model.User;
import com.system.carRentalManagementSystem.model.Vehicle;
import com.system.carRentalManagementSystem.repository.BookingRepository;
import com.system.carRentalManagementSystem.repository.DriverRepository;
import com.system.carRentalManagementSystem.repository.UserRepository;
import com.system.carRentalManagementSystem.repository.VehicleRepository;
import lombok.SneakyThrows;
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

    @SneakyThrows
    public Booking getBookingById(Long bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);

        if (booking.isPresent()) {
            return booking.get();
        } else {
            throw new EntityNotPresentException("There is no such booking!");
        }
    }

    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    @SneakyThrows
    public Booking getUserBookingById(Long userId, Long bookingId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Booking> booking = bookingRepository.findById(bookingId);

        if (user.isPresent() && booking.isPresent()) {
            if (user.get().hasBooking(bookingId)) {
                return booking.get();
            } else {
                throw new NullDataException("This user does not have such bookings!");
            }
        } else {
            throw new EntityNotPresentException("There is no such booking or user!");
        }
    }

    @SneakyThrows
    public Booking createBookingWithoutDriver(Booking booking, Long userId, Long vehicleId) {
        if (booking.getEndDate() == null) {
            throw new NullDataException("End date cannot be empty!");
        }

        Optional<User> user = userRepository.findById(userId);
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        if (vehicle.isPresent() && user.isPresent()) {
            booking.setUser(user.get());
            booking.setVehicle(vehicle.get());

            vehicle.get().setAvailability(false);

            return bookingRepository.save(booking);
        } else {
            throw new EntityNotPresentException("There is no such vehicle or user!");
        }
    }

    @SneakyThrows
    public Booking createBookingWithDriver(Booking booking, Long userId, Long vehicleId, Long driverId) {
        if (booking.hasDriver()) {
            Optional<User> user = userRepository.findById(userId);
            Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
            Optional<Driver> driver = driverRepository.findById(driverId);

            if (user.isPresent() && vehicle.isPresent() && driver.isPresent()) {
                booking.setUser(user.get());
                booking.setVehicle(vehicle.get());
                driver.get().setBooking(booking);

                vehicle.get().setAvailability(false);

                return bookingRepository.save(booking);
            } else {
                throw new EntityNotPresentException("There is no such user, vehicle or driver!");
            }

        } else {
            throw new NullDataException("This booking does not have driver!");
        }
    }

    @SneakyThrows
    public void deleteBooking(Long userId, Long bookingId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Booking> booking = bookingRepository.findById(bookingId);

        if(user.isPresent() && booking.isPresent()) {

            if (user.get().hasBooking(bookingId)) {
                booking.get().getVehicle().setAvailability(true);

                bookingRepository.deleteById(bookingId);
            } else {
                throw new NullDataException("This user does not have such booking!");
            }

        } else {
            throw new EntityNotPresentException("There is no such booking or user!");
        }
    }

    @SneakyThrows
    public Driver getDriverByBookingId(Long userId, Long bookingId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Booking> booking = bookingRepository.findById(bookingId);

        if (user.isPresent() && booking.isPresent()) {
            Driver driver = driverRepository.findByBookingId(bookingId);

            if (booking.get().hasDriver()) {

                if (user.get().hasBooking(bookingId) && driver.getBooking().equals(booking.get())) {
                    return driver;
                } else {
                    throw new NullDataException("This user does not have such booking or \n This driver have a different booking!");
                }

            } else {
                throw new NullDataException("This booking does not have driver!");
            }

        } else {
            throw new EntityNotPresentException("There is no such booking, user or driver!");
        }
    }

    @SneakyThrows
    public Driver setDriverByBookingId(Long userId, Long bookingId, Long driverId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        Optional<Driver> driver = driverRepository.findById(driverId);

        if (user.isPresent() && booking.isPresent() && driver.isPresent()) {

            if (user.get().hasBooking(bookingId)) {
                driver.get().setBooking(booking.get());
                booking.get().setHasDriver(true);

                return driverRepository.save(driver.get());
            } else {
                throw new NullDataException("This user does not have such booking!");
            }

        } else {
            throw new EntityNotPresentException("There is no such booking, user or driver!");
        }
    }
}