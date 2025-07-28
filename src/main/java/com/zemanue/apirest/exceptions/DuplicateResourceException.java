package com.zemanue.apirest.exceptions;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
    
    public DuplicateResourceException(String resource, String field, String value) {
        super("Ya existe un " + resource + " con " + field + ": " + value);
    }
}
