package com.system.carRentalManagementSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EntityNotPresentException extends Exception {
    public EntityNotPresentException(String message) {
        super(message);
    }
}
