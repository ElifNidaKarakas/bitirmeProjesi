package com.finansTakipSistemi.bitirmeProjesi.exception;

public class InvalidTransactionException extends RuntimeException {
    public InvalidTransactionException(String message) {
        super(message);
    }
}