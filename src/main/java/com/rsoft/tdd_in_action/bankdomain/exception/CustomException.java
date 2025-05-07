package com.rsoft.tdd_in_action.bankdomain.exception;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
