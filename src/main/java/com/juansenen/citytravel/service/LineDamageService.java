package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.domain.LineDamage;
import com.juansenen.citytravel.exception.LineNoFoundException;

import java.util.List;

public interface LineDamageService {

    //Listar todos
    List<LineDamage> findAll();

    //Buscar por id
    LineDamage findById(long id) throws LineNoFoundException;

    //Añadir uno
    LineDamage add(LineDamage lineDamage);

    //Borrar uno
    void delDamage (long id) throws LineNoFoundException;
    //Modificar uno
    LineDamage modDamage(long id, LineDamage lineDamage) throws LineNoFoundException;
}
