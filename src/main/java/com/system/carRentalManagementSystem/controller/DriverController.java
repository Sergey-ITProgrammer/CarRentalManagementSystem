package com.system.carRentalManagementSystem.controller;

import com.system.carRentalManagementSystem.model.Driver;
import com.system.carRentalManagementSystem.modelAssembler.DriverModelAssembler;
import com.system.carRentalManagementSystem.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    private final DriverService driverService;

    private final DriverModelAssembler modelAssembler;

    @Autowired
    public DriverController(DriverService driverService, DriverModelAssembler modelAssembler) {
        this.driverService = driverService;
        this.modelAssembler = modelAssembler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDriverById(@PathVariable Long id) {
        EntityModel<Driver> entityModel = modelAssembler.toModel(driverService.getDriverById(id));

        return ResponseEntity.ok(entityModel);
    }

    @GetMapping("")
    public ResponseEntity<?> getDrivers() {
        CollectionModel<EntityModel<Driver>> entityModels = modelAssembler.toCollectionModel(driverService.getDrivers());

        return ResponseEntity.ok(entityModels);
    }

    @PostMapping("")
    public ResponseEntity<?> createDriver(@RequestBody Driver driver) {
        EntityModel<Driver> entityModel = modelAssembler.toModel(driverService.createDriver(driver));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDriver(@RequestBody Driver driver, @PathVariable Long id) {
        EntityModel<Driver> entityModel = modelAssembler.toModel(driverService.updateDriver(driver, id));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDriver(@PathVariable("id") Long id) {
        driverService.deleteDriverById(id);

        return ResponseEntity.noContent().build();
    }
}
