package com.system.carRentalManagementSystem.modelAssembler;

import com.system.carRentalManagementSystem.controller.UserController;
import com.system.carRentalManagementSystem.controller.VehicleController;
import com.system.carRentalManagementSystem.model.User;
import com.system.carRentalManagementSystem.model.Vehicle;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class VehicleModelAssembler implements RepresentationModelAssembler<Vehicle, EntityModel<Vehicle>> {
    @Override
    public EntityModel<Vehicle> toModel(Vehicle entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(VehicleController.class).getVehicleById(entity.getId())).withSelfRel(),
                linkTo(methodOn(VehicleController.class).getAllVehicles()).withRel("vehicles"));
    }


    @Override
    public CollectionModel<EntityModel<Vehicle>> toCollectionModel(Iterable<? extends Vehicle> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
