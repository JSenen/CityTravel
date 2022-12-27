package com.juansenen.citytravel.exception;


public class NotFoundException extends Exception{

    //Excepción genérica para recoger todas. Nos plasma la clase que causa la excepción más el texto que asignamos
    public NotFoundException(Object obj){
        super(obj.getClass().getSimpleName() + " NOT FOUND");
    }
}
