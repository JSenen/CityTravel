package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.exception.LineNoFoundException;

import java.util.List;
import java.util.Optional;

public interface LineService {

    //Listar todos
    List<Line> findAll();

    //Buscar por id
    Line findById(long id) throws LineNoFoundException;

    //Añadir uno
    Line add(Line line);

    //Borrar uno
    void deleteLine(long id) throws LineNoFoundException;


}
