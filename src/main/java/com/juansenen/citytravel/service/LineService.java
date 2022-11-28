package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.domain.LineTrain;
import com.juansenen.citytravel.exception.LineNoFoundException;

import java.util.List;

public interface LineService {


    List<Line> findAll();
    Line findById(long id) throws LineNoFoundException;
    Line add(Line line) throws LineNoFoundException;
    void deleteLine (long id) throws LineNoFoundException;
    Line modyLine(long id, Line line) throws LineNoFoundException;

}