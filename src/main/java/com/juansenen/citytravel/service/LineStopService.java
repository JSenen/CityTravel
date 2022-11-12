package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.LineStop;
import com.juansenen.citytravel.exception.LineNoFoundException;

import java.util.List;

public interface LineStopService {
    List<LineStop> findAll();
    LineStop findById(long id) throws LineNoFoundException;
    LineStop addLinStop(LineStop linestop);
    void dellLinStop (long id) throws LineNoFoundException;
    LineStop modLinStop(long id, LineStop linestop) throws LineNoFoundException;
}
