package br.com.contasapagar.services.exceptions;

public class ResourceNotExistsException extends RuntimeException{

    public ResourceNotExistsException(){

    }

    public ResourceNotExistsException(String message){
        super(message);
    }
}
