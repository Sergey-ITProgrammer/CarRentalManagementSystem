package com.system.carRentalManagementSystem.modelAssembler;

import com.system.carRentalManagementSystem.controller.DriverController;
import com.system.carRentalManagementSystem.model.Driver;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DriverModelAssembler implements RepresentationModelAssembler<Driver, EntityModel<Driver>> {
    @Override
    public EntityModel<Driver> toModel(Driver entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(DriverController.class).getDriverById(entity.getId())).withSelfRel(),
                linkTo(methodOn(DriverController.class).getDrivers()).withRel("drivers"));
    }

    @Override
    public CollectionModel<EntityModel<Driver>> toCollectionModel(Iterable<? extends Driver> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
