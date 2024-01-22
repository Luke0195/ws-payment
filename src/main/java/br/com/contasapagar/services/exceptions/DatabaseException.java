package br.com.contasapagar.services.exceptions;

public class DatabaseException extends RuntimeException{

    public DatabaseException(){}

    public DatabaseException(String msg){
        super(msg);
    }
}
