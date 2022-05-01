package com.system.carRentalManagementSystem.controller;

import com.system.carRentalManagementSystem.model.Booking;
import com.system.carRentalManagementSystem.model.Driver;
import com.system.carRentalManagementSystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{userId}/bookings")
    public List<Booking> getBookingsByUserId(@PathVariable("userId") Long userId) {
        return bookingService.getBookingsByUserId(userId);
    }

    @GetMapping("{userId}/bookings/{bookingId}")
    public Booking getUserBookingById(@PathVariable("userId") Long userId, @PathVariable("bookingId") Long bookingId) {
        return bookingService.getUserBookingById(userId, bookingId);
    }

    @PostMapping("/{userId}/bookings")
    public Booking createBookingWithoutDriver(@RequestBody Booking booking, @PathVariable("userId") Long userId,
                                              @RequestParam("vehicleId") Long vehicleId) {
        return bookingService.createBookingWithoutDriver(booking, userId, vehicleId);
    }

    @PostMapping("/{userId}/bookings/withDriver")
    public Booking createBookingWithDriver(@RequestBody Booking booking, @PathVariable("userId") Long userId,
                                           @RequestParam("vehicleId") Long vehicleId, @RequestParam("driverId") Long driverId) {
        return bookingService.createBookingWithDriver(booking, userId, vehicleId, driverId);
    }

    @DeleteMapping("/{userId}/bookings/{bookingId}")
    public void deleteBooking(@PathVariable("userId") Long userId, @PathVariable("bookingId") Long bookingId) {
        bookingService.deleteBooking(userId, bookingId);
    }

    @GetMapping("/{userId}/bookings/{bookingId}/driver")
    public Driver getDriver(@PathVariable("userId") Long userId,
                            @PathVariable("bookingId") Long bookingId, @RequestParam("driverId") Long driverId) {
        return bookingService.getDriver(userId, bookingId, driverId);
    }

    @PatchMapping("/{userId}/bookings/{bookingId}")
    public Driver setDriver(@PathVariable("userId") Long userId,
                            @PathVariable("bookingId") Long bookingId, @RequestParam("driverId") Long driverId) {
        return bookingService.setDriver(userId, bookingId, driverId);
    }
}