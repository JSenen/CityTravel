package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.domain.dto.LineDTO;
import com.juansenen.citytravel.exception.LineNoFoundException;

import java.util.List;

public interface LineService {


    List<Line> findAll();
    Line findById(long id) throws LineNoFoundException;
    Line add(LineDTO linedto) throws LineNoFoundException;
    void deleteLine (long id) throws LineNoFoundException;
    Line modyLine(long id, Line line) throws LineNoFoundException;





}
