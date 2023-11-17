package com.astontech.resttest.exceptions;

public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException(String value) {
        super("Entity already exists with unique value " + value);
    }
}
