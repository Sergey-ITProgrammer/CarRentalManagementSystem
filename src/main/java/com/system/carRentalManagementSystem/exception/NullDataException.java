package com.system.carRentalManagementSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NullDataException extends Exception {
    public NullDataException(String message) {
        super(message);
    }
}
