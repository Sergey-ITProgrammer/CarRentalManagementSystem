package com.system.carRentalManagementSystem.loader;

import com.system.carRentalManagementSystem.model.Booking;
import com.system.carRentalManagementSystem.model.Driver;
import com.system.carRentalManagementSystem.model.User;
import com.system.carRentalManagementSystem.model.Vehicle;
import com.system.carRentalManagementSystem.service.BookingService;
import com.system.carRentalManagementSystem.service.DriverService;
import com.system.carRentalManagementSystem.service.UserService;
import com.system.carRentalManagementSystem.service.VehicleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserService userService, VehicleService vehicleService,
                                   BookingService bookingService, DriverService driverService) {
        return args -> {
            User user1 = userService.createUser(new User("Sergey", "someCoNo", "tsbrbe@email.com", "2000-12-12"));
            User user2 = userService.createUser(new User("Sergey", "someCoNo", "tsbragrfae@email.com", "2000-04-23"));
            User user3 = userService.createUser(new User("Sergey", "someCoNo", "sefbrbe@email.com", "2000-05-04"));

            Vehicle vehicle1 = vehicleService.createVehicle(new Vehicle("sdfvd", "someModel"));
            Vehicle vehicle2 = vehicleService.createVehicle(new Vehicle("egareav", "someModel"));
            Vehicle vehicle3 = vehicleService.createVehicle(new Vehicle("rehtrsn", "someModel"));

            Driver driver1 = driverService.createDriver(new Driver("someName", 24L, "JKAERN", "STRH", "erabr"));

            Booking booking1 = bookingService.createBookingWithoutDriver(new Booking(false, "2022-05-06"), user1.getId(), vehicle1.getId());
            Booking booking2 = bookingService.createBookingWithoutDriver(new Booking(false, "2022-05-06"), user1.getId(), vehicle2.getId());
            Booking booking3 = bookingService.createBookingWithoutDriver(new Booking(false, "2022-05-06"), user2.getId(), vehicle3.getId());
        };
    }
}
