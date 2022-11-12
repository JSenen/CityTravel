package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.domain.LineType;
import com.juansenen.citytravel.exception.LineNoFoundException;

import java.util.List;
import java.util.Optional;

public interface LineTypeService {

    List<LineType> findAll();
    Optional<LineType> findById(long id) ;
    LineType add(LineType linetype);
    void delLineTyp (long id) throws LineNoFoundException;
    LineType modLineTyp(long id, LineType linetype) throws LineNoFoundException;
}
