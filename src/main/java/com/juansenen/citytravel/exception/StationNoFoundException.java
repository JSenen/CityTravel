package com.juansenen.citytravel.exception;

public class StationNoFoundException extends Exception{

    public StationNoFoundException(){
        super("Station not found");
    }
    public StationNoFoundException(String message) {
        super(message);
    }
}
