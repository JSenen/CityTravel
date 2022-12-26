package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.domain.dto.outLineDTO;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.exception.NotFoundException;

import java.time.LocalTime;
import java.util.List;

public interface LineService {


    List<outLineDTO> findAll();
    Line findById(long id) throws NotFoundException;
    Line add(Line line);
    void deleteLine (long id) throws  NotFoundException;
    Line modyLine(long id, Line line) throws NotFoundException;


    List<outLineDTO> searchByHourStartAndHourClose(LocalTime start, LocalTime hclose);

    Line updateLine(long id, Line line) throws NotFoundException;
}
