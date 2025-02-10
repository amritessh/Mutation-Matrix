package com.mutationmatrix.variant_calling_service.exception;

public class SequenceNotFoundException extends RuntimeException {
    public SequenceNotFoundException(String message) {
        super(message);
    }
}