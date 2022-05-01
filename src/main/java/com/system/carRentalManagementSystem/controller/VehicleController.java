package com.system.carRentalManagementSystem.controller;

import com.system.carRentalManagementSystem.model.Vehicle;
import com.system.carRentalManagementSystem.modelAssembler.VehicleModelAssembler;
import com.system.carRentalManagementSystem.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    private final VehicleModelAssembler modelAssembler;

    @Autowired
    public VehicleController(VehicleService vehicleService, VehicleModelAssembler modelAssembler) {
        this.vehicleService = vehicleService;
        this.modelAssembler = modelAssembler;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllVehicles() {
        CollectionModel<EntityModel<Vehicle>> entityModels = modelAssembler.toCollectionModel(vehicleService.getAllVehicles());

        return ResponseEntity.ok(entityModels);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<?> getVehicleById(@PathVariable("vehicleId") Long vehicleId) {
        EntityModel<Vehicle> entityModel = modelAssembler.toModel(vehicleService.getVehicleById(vehicleId));

        if(entityModel.getContent() == null) {
            return ResponseEntity.badRequest().body("Bad request");
        }

        return ResponseEntity.ok(modelAssembler);
    }

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableVehicles() {
        CollectionModel<EntityModel<Vehicle>> entityModels = modelAssembler.toCollectionModel(vehicleService.getAvailableVehicles());

        return ResponseEntity.ok(entityModels);
    }

    @GetMapping("/unavailable")
    public ResponseEntity<?> getUnavailableVehicles() {
        CollectionModel<EntityModel<Vehicle>> entityModels = modelAssembler.toCollectionModel(vehicleService.getUnavailableVehicles());

        return ResponseEntity.ok(entityModels);
    }

    @PostMapping("")
    public ResponseEntity<?> createVehicle(@RequestBody Vehicle vehicle) {
        EntityModel<Vehicle> entityModel = modelAssembler.toModel(vehicleService.createVehicle(vehicle));

        if (entityModel.getContent() == null) {
            return ResponseEntity.badRequest().body("Bad request");
        }

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<?> updateVehicle(@RequestBody Vehicle newVehicle, @PathVariable("vehicleId") Long vehicleId) {
        EntityModel<Vehicle> entityModel = modelAssembler.toModel(vehicleService.updateVehicle(newVehicle, vehicleId));

        if (entityModel.getContent() == null) {
            return ResponseEntity.badRequest().body("Bad request");
        }

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PatchMapping("/{vehicleId}")
    public ResponseEntity<?> updateAvailability(@PathVariable("vehicleId") Long vehicleId, @RequestParam boolean available) {
        EntityModel<Vehicle> entityModel = modelAssembler.toModel(vehicleService.updateAvailabilityById(vehicleId, available));

        if (entityModel.getContent() == null) {
            return ResponseEntity.badRequest().body("Bad request");
        }

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<?> deleteVehicleById(@PathVariable("vehicleId") Long vehicleId) {
        vehicleService.deleteVehicleById(vehicleId);

        return ResponseEntity.noContent().build();
    }
}
