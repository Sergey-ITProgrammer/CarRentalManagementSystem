package com.system.carRentalManagementSystem.controller;

import com.system.carRentalManagementSystem.model.User;
import com.system.carRentalManagementSystem.modelAssembler.UserModelAssembler;
import com.system.carRentalManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    private final UserModelAssembler modelAssembler;

    @Autowired
    public UserController(UserService userService, UserModelAssembler modelAssembler) {
        this.userService = userService;
        this.modelAssembler = modelAssembler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        EntityModel<User> entityModel = modelAssembler.toModel(userService.getUserById(id));

        if (entityModel.getContent() == null) {
            return ResponseEntity.badRequest().body("Bad request");
        }

        return ResponseEntity.ok(entityModel);
    }

    @GetMapping("")
    public ResponseEntity<?> getUsers() {
        CollectionModel<EntityModel<User>> entityModels = modelAssembler.toCollectionModel(userService.getUsers());

        return ResponseEntity.ok(entityModels);
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        EntityModel<User> entityModel = modelAssembler.toModel(userService.createUser(user));

        if (entityModel.getContent() == null) {
            return ResponseEntity.badRequest().body("Bad request");
        }

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Long id) {
        EntityModel<User> entityModel = modelAssembler.toModel(userService.updateUser(user, id));

        if (entityModel.getContent() == null) {
            return ResponseEntity.badRequest().body("Bad request");
        }

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);

        return ResponseEntity.noContent().build();
    }
}