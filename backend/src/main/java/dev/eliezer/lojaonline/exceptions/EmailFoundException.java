package dev.eliezer.lojaonline.exceptions;

public class EmailFoundException extends RuntimeException{
    public EmailFoundException(String email) {
        super("E-mail " + email + " is alright in use");
    }
}
