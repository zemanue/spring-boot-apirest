package com.zemanue.apirest.exceptions;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(Long id) {
        super("Usuario con ID " + id + " no encontrado.");
    }

    public UsuarioNotFoundException(String message) {
        super(message);
    }
}
