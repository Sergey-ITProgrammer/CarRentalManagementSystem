package com.system.carRentalManagementSystem;

import com.system.carRentalManagementSystem.model.Booking;
import com.system.carRentalManagementSystem.model.User;
import com.system.carRentalManagementSystem.model.Vehicle;
import com.system.carRentalManagementSystem.service.BookingService;
import com.system.carRentalManagementSystem.service.UserService;
import com.system.carRentalManagementSystem.service.VehicleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarRentalManagementSystemApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private BookingService bookingService;

	@Test
	void testUserBookingVehicle() {
//		User user1 = new User("Sergey", "SomeNo", "example@gmail.com", "02.05.2000", "someLicence");
//		User user2 = new User("Alex", "SomeNo", "example@gmail.com", "02.05.2000", "someLicence");
//		User user3 = new User("Andrey", "SomeNo", "example@gmail.com", "02.05.2000", "someLicence");
//
//		Vehicle vehicle1 = new Vehicle("q3f34", "ewfe", false);
//		Vehicle vehicle2 = new Vehicle("2223e3", "222ccaer", false);
//		Vehicle vehicle3 = new Vehicle("33ds", "33eacw", true);
//
//		userService.createUser(user1);
//		userService.createUser(user2);
//
//		vehicleService.addVehicle(vehicle1);
//		vehicleService.addVehicle(vehicle2);
//		vehicleService.addVehicle(vehicle3);
//
//		Booking booking1 = new Booking(user1, vehicle1, true);
//		Booking booking2 = new Booking(user1, vehicle2, false);
//		Booking booking3 = new Booking(user2, vehicle3, true);
//
//		bookingService.addBooking(booking1);
//		bookingService.addBooking(booking2);
//		bookingService.addBooking(booking3);

//		user1.getBookings().add(booking1);
//		user1.getBookings().add(booking2);
//
//		user2.getBookings().add(booking3);
//
//		userService.createUser(user1);
//		userService.createUser(user2);
//
//		Assertions.assertEquals(user1.getBookings().size(), 2);
//		Assertions.assertEquals(userService.getUserById(1L).getName(), "Sergey");
//		Assertions.assertEquals(userService.getUserById(1L).getBookings().size(), 2);
//		Assertions.assertEquals(userService.getUserById(2L).getBookings().size(), 1);
//
//		// cannot be created bookings with the same vehicles
//		Booking booking4 = new Booking(user1, vehicle3, true);
//		Assertions.assertThrows(org.springframework.dao.DataIntegrityViolationException.class, () -> bookingService.addBooking(booking4));
//
//
//		Vehicle vehicle4 = new Vehicle("44dvef", "44frevs", true);
//		vehicleService.addVehicle(vehicle4);
//
//		// cannot be created bookings with null user
//		Booking booking5= new Booking(null, vehicle4, false);
//		Assertions.assertThrows(org.springframework.dao.DataIntegrityViolationException.class, () -> bookingService.addBooking(booking5));
//
//		userService.deleteUserById(1L);
//
//		// onDelete
//		Assertions.assertEquals(bookingService.getAllBookings().size(), 1);
	}

}
