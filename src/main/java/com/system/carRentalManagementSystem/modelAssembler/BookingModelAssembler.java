package com.system.carRentalManagementSystem.modelAssembler;

import com.system.carRentalManagementSystem.controller.BookingController;
import com.system.carRentalManagementSystem.model.Booking;
import com.system.carRentalManagementSystem.model.Driver;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookingModelAssembler implements RepresentationModelAssembler<Booking, EntityModel<Booking>> {
    @Override
    public EntityModel<Booking> toModel(Booking entity) {
        return null;
    }

    public EntityModel<Booking> toModel(Booking entity, Long userId) {
        return EntityModel.of(entity,
                linkTo(methodOn(BookingController.class).getUserBookingById(entity.getId(), userId)).withSelfRel(),
                linkTo(methodOn(BookingController.class).getBookingsByUserId(userId)).withRel("bookings"));
    }

    public EntityModel<Driver> toModel(Driver entity, Long userId, Long bookingId) {
        return EntityModel.of(entity,
                linkTo(methodOn(BookingController.class).getDriverByBookingId(userId, bookingId)).withSelfRel(),
                linkTo(methodOn(BookingController.class).getBookingsByUserId(userId)).withRel("bookings"));
    }

    @Override
    public CollectionModel<EntityModel<Booking>> toCollectionModel(Iterable<? extends Booking> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }

    public CollectionModel<EntityModel<Booking>> toCollectionModel(List<Booking> entities, Long userId) {

        List<EntityModel<Booking>> list = new ArrayList<>();

            for (Booking b : entities) {
                list.add(toModel(b, userId));
            }


        return CollectionModel.of(list, linkTo(methodOn(BookingController.class).getBookingsByUserId(userId)).withRel("bookings"));
    }
}
