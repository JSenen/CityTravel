package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.domain.LineInfo;
import com.juansenen.citytravel.exception.LineNoFoundException;

import java.util.List;

public interface LineInfoService {

    //Listar todos
    List<LineInfo> findAll();

    //Buscar por id
    LineInfo findById(long id) throws LineNoFoundException;

    //Añadir uno
    LineInfo add(LineInfo lineinfo);

    //Borrar uno
    void delLinInfo (long id) throws LineNoFoundException;
    //Modificar uno
    LineInfo lineInfmod (long id, LineInfo lineinfo) throws LineNoFoundException;


}
