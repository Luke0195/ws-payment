package br.com.contasapagar.services.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException() {
    }
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
