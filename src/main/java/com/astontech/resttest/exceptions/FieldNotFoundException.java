package com.astontech.resttest.exceptions;

public class FieldNotFoundException extends RuntimeException {

    public FieldNotFoundException(String fieldName) {
        super("Object does not contain field: " + fieldName);
    }
}
