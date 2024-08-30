package com.anderson.address_api.entrypoint.exceptions.controller;

import com.anderson.address_api.core.exceptions.DataConflictException;
import com.anderson.address_api.core.exceptions.NotFoundException;
import com.anderson.address_api.core.exceptions.StandardException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

import static com.anderson.address_api.core.exceptions.constants.ExceptionConstants.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardException> notFound(NotFoundException e, HttpServletRequest request) {
        StandardException exception = new StandardException(Instant.now(), HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.status()).body(exception);
    }

    @ExceptionHandler(DataConflictException.class)
    public ResponseEntity<StandardException> dataConflict(DataConflictException e, HttpServletRequest request) {
        StandardException exception = new StandardException(Instant.now(), HttpStatus.CONFLICT.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.status()).body(exception);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardException> exception(Exception e, HttpServletRequest request) {
        StandardException exception = new StandardException(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR, request.getRequestURI());
        return ResponseEntity.status(exception.status()).body(exception);
    }

}
