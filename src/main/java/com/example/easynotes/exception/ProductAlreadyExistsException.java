package com.example.easynotes.exception;

public class ProductAlreadyExistsException extends Exception{
    private String message;

    public ProductAlreadyExistsException(){

    }

    public ProductAlreadyExistsException(String message){
        super();
        this.message = message;
    }

}