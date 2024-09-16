package dev.eliezer.lojaonline.exceptions;

public class UnauthorizedAccessException extends RuntimeException {

    public UnauthorizedAccessException(Long id) {

        super("User with id " + id + " does not have access to this resource.");
    }
}
