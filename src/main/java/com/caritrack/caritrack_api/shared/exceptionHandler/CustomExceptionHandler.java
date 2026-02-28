package com.caritrack.caritrack_api.shared.exceptionHandler;

import com.caritrack.caritrack_api.association.utils.exceptions.AssociationNotFoundException;
import com.caritrack.caritrack_api.item.utils.exceptions.ItemNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.management.relation.RoleNotFoundException;
import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> handleMethodArgumentNotValidException(MethodArgumentNotValidException manve) {

        CustomError error = new CustomError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                manve.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomError> handleConstraintViolationException(ConstraintViolationException cve) {
        CustomError error = new CustomError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                cve.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AssociationNotFoundException.class,RoleNotFoundException.class, ItemNotFoundException.class})
    public ResponseEntity<CustomError> handleUserNotFoundException(Exception nfe) {
        CustomError error = new CustomError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                nfe.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CustomError> handleDataIntegrityException(DataIntegrityViolationException dive) {
        String message = dive.getMessage();
        if (message.contains("unique constraint")) message = "{error.integrity.exception}";

        CustomError error = new CustomError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                message
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(DataAccessResourceFailureException.class)
    public ResponseEntity<CustomError> handleDatabaseDown(DataAccessResourceFailureException ex) {

        CustomError error = new CustomError(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "{error.database.connection}"
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
