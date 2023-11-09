package com.pragma.powerup.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    NO_DATA_FOUND("No data found for the requested petition"),
    INVALID_EMAIL("The email format is invalid"),
    INVALID_PHONE("The phone format is invalid. It must start with a '+' followed by a maximum of 13 numbers"),
    INVALID_FORMAT("The format of at least one JSON attribute value is invalid. Check that the input data is correct"),
    INVALID_BIRTH_DATE("The user must be over 18 years old"),
    SQL_INTEGRITY_CONSTRAINT_VIOLATION("The user with the identity document number already exists"),

    INVALID_IDENTITY_DOCUMENT("The identity document must be a number");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}