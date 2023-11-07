package com.pragma.powerup.infrastructure.exceptionhandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.pragma.powerup.domain.exception.InvalidEmailException;
import com.pragma.powerup.domain.exception.InvalidIdentityDocumentException;
import com.pragma.powerup.domain.exception.InvalidPhoneException;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }
    @ExceptionHandler(InvalidIdentityDocumentException.class)
    public ResponseEntity<Map<String, String>> handleInvalidIdentityDocumentException(
            InvalidIdentityDocumentException ignoredException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_IDENTITY_DOCUMENT.getMessage()));
    }
    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<Map<String, String>> handleInvalidEmailException(
            InvalidEmailException ignoredException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_EMAIL.getMessage()));
    }
    @ExceptionHandler(InvalidPhoneException.class)
    public ResponseEntity<Map<String, String>> handleInvalidPhoneException(
            InvalidPhoneException ignoredException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_PHONE.getMessage()));
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Map<String, String>> handleInvalidFormatException(
            InvalidFormatException ignoredException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_FORMAT.getMessage()));
    }
}