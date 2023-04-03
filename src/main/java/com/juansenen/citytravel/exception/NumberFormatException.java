package com.juansenen.citytravel.exception;

public class NumberFormatException extends Exception{
    public NumberFormatException(){
        super("Number not valid");
    }
    public NumberFormatException(String message) {
        super(message);
    }
}
