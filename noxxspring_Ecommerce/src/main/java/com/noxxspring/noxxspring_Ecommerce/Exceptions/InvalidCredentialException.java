package com.noxxspring.noxxspring_Ecommerce.Exceptions;

public class InvalidCredentialException extends RuntimeException{
    public InvalidCredentialException(String message){
        super(message);
    }
}
