package com.system.carRentalManagementSystem.controller;

import com.system.carRentalManagementSystem.model.Booking;
import com.system.carRentalManagementSystem.model.Driver;
import com.system.carRentalManagementSystem.modelAssembler.BookingModelAssembler;
import com.system.carRentalManagementSystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class BookingController {
    private final BookingService bookingService;

    private final BookingModelAssembler modelAssembler;

    @Autowired
    public BookingController(BookingService bookingService, BookingModelAssembler modelAssembler) {
        this.bookingService = bookingService;
        this.modelAssembler = modelAssembler;
    }

    @GetMapping("/bookings")
    public ResponseEntity<?> getAllBookings() {
        CollectionModel<EntityModel<Booking>> entityModels = modelAssembler.toCollectionModel(bookingService.getAllBookings());

        return ResponseEntity.ok(entityModels);
    }

    @GetMapping("/bookings/{bookingId}")
    public ResponseEntity<?> getBookingById(@PathVariable("bookingId") Long bookingId) {
        EntityModel<Booking> entityModel = modelAssembler.toModel(bookingService.getBookingById(bookingId));

        return ResponseEntity.ok(entityModel);
    }

    @GetMapping("/{userId}/bookings")
    public ResponseEntity<?> getBookingsByUserId(@PathVariable("userId") Long userId) {
        CollectionModel<EntityModel<Booking>> entityModels = modelAssembler.toCollectionModel(bookingService.getBookingsByUserId(userId), userId);

        return ResponseEntity.ok(entityModels);
    }

    @GetMapping("{userId}/bookings/{bookingId}")
    public ResponseEntity<?> getUserBookingById(@PathVariable("userId") Long userId, @PathVariable("bookingId") Long bookingId) {
        EntityModel<Booking> entityModel = modelAssembler.toModel(bookingService.getUserBookingById(userId, bookingId), userId);

        return ResponseEntity.ok(entityModel);
    }

    @PostMapping("/{userId}/bookings")
    public ResponseEntity<?> createBookingWithoutDriver(@RequestBody Booking booking, @PathVariable("userId") Long userId,
                                                                           @RequestParam("vehicleId") Long vehicleId) {
        EntityModel<Booking> entityModel
                = modelAssembler.toModel(bookingService.createBookingWithoutDriver(booking, userId, vehicleId), userId);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PostMapping("/{userId}/bookings/withDriver")
    public ResponseEntity<?> createBookingWithDriver(@RequestBody Booking booking, @PathVariable("userId") Long userId,
                                           @RequestParam("vehicleId") Long vehicleId, @RequestParam("driverId") Long driverId) {
        EntityModel<Booking> entityModel
                = modelAssembler.toModel(bookingService.createBookingWithDriver(booking, userId, vehicleId, driverId), userId);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/{userId}/bookings/{bookingId}")
    public ResponseEntity<?> deleteBooking(@PathVariable("userId") Long userId, @PathVariable("bookingId") Long bookingId) {
        bookingService.deleteBooking(userId, bookingId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/bookings/{bookingId}/driver")
    public ResponseEntity<?> getDriverByBookingId(@PathVariable("userId") Long userId,
                                                  @PathVariable("bookingId") Long bookingId) {
        EntityModel<Driver> entityModel
                = modelAssembler.toModel(bookingService.getDriverByBookingId(userId,bookingId), userId, bookingId);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PatchMapping("/{userId}/bookings/{bookingId}")
    public ResponseEntity<?> setDriverByBookingId(@PathVariable("userId") Long userId,
                            @PathVariable("bookingId") Long bookingId, @RequestParam("driverId") Long driverId) {
        EntityModel<Driver> entityModel
                = modelAssembler.toModel(bookingService.setDriverByBookingId(userId, bookingId, driverId), userId, bookingId);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }
}