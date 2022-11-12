package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.Line;

import java.util.List;

public interface LineService {

    //Listar todos
    List<Line> findAll();

    //Buscar por id
    Line findById(long id);

    //Añadir uno
    Line add(Line line);

    //Borrar uno
    void deleteLine(long id);

    //Modificar una linea
    void modiLine(long id, Line line);

}
