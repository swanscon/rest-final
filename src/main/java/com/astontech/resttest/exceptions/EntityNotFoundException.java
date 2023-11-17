package com.astontech.resttest.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String id) {
        super("Could not find entity with ID: " +id);
    }
}
