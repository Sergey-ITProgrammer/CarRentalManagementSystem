package com.system.carRentalManagementSystem.modelAssembler;

import com.system.carRentalManagementSystem.controller.BookingController;
import com.system.carRentalManagementSystem.controller.UserController;
import com.system.carRentalManagementSystem.model.Booking;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class BookingModelAssembler implements RepresentationModelAssembler<Booking, EntityModel<Booking>> {
    @Override
    public EntityModel<Booking> toModel(Booking entity) {
        return null;
    }

    public EntityModel<Booking> toModel(Booking entity, Long userId) {
        return EntityModel.of(entity,
                linkTo(methodOn(BookingController.class).getUserBookingById(entity.getId(), userId)).withSelfRel(),
                linkTo(methodOn(BookingController.class).getBookingsByUserId(userId)).withRel("users"));
    }

    @Override
    public CollectionModel<EntityModel<Booking>> toCollectionModel(Iterable<? extends Booking> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
