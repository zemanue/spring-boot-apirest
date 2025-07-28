package com.zemanue.apirest.exceptions;

import java.util.ArrayList;
import java.util.List;

public class InvalidDataException extends RuntimeException {
    private List<String> errors;
    
    public InvalidDataException(String message) {
        super(message);
        this.errors = new ArrayList<>();
        this.errors.add(message);
    }
    
    public InvalidDataException(List<String> errors) {
        super(buildMessage(errors));
        this.errors = errors;
    }
    
    public InvalidDataException(String field, String value, String reason) {
        super("Datos inválidos para el campo '" + field + "' con valor '" + value + "': " + reason);
        this.errors = new ArrayList<>();
        this.errors.add("Campo '" + field + "': " + reason);
    }
    
    public List<String> getErrors() {
        return errors;
    }
    
    private static String buildMessage(List<String> errors) {
        if (errors.size() == 1) {
            return errors.get(0);
        }
        return "Se encontraron " + errors.size() + " errores de validación";
    }
    
    // Métodos estáticos para crear excepciones específicas
    public static InvalidDataException required(String field) {
        return new InvalidDataException(field, "vacío", "es obligatorio");
    }
    
    public static InvalidDataException invalidFormat(String field, String value, String expectedFormat) {
        return new InvalidDataException(field, value, "formato inválido. Se esperaba: " + expectedFormat);
    }
    
    public static InvalidDataException tooShort(String field, String value, int minLength) {
        return new InvalidDataException(field, value, "debe tener al menos " + minLength + " caracteres");
    }
    
    public static InvalidDataException tooLong(String field, String value, int maxLength) {
        return new InvalidDataException(field, value, "no puede tener más de " + maxLength + " caracteres");
    }
}
