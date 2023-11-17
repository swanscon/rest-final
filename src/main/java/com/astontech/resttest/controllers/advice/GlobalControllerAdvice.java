package com.astontech.resttest.controllers.advice;

import com.astontech.resttest.exceptions.EntityAlreadyExistsException;
import com.astontech.resttest.exceptions.EntityNotFoundException;
import com.astontech.resttest.exceptions.FieldNotFoundException;
import com.astontech.resttest.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String entityAlreadyFoundHandler(EntityAlreadyExistsException aEx) {
        return aEx.getLocalizedMessage();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String entityNotFoundHandler(EntityNotFoundException eEx) {
        return eEx.getLocalizedMessage();
    }

    @ExceptionHandler(FieldNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String fieldNotFoundHandler(FieldNotFoundException fEx) {
        return fEx.getLocalizedMessage();
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String unauthorizedHandler(UnauthorizedException uEx) {
        return uEx.getLocalizedMessage();
    }

    // General Exception Catch
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String generalExceptionHandler(Exception ex) {
        return "General Server Error";
    }
}
