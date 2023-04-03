package com.juansenen.citytravel.exception;

public class LineNoFoundException extends Exception{

    public LineNoFoundException(){

        super("Line not found");
    }
    public LineNoFoundException(String message) {

        super(message);
    }
 }

 //TODO Crear resto de excepciones