package com.noxxspring.noxxspring_Ecommerce.Exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException (String message){
        super(message);
    }
}
