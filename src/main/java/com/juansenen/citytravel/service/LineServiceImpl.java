package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineServiceImpl implements LineService {

    @Autowired
    LineRepository lineRepository;

    //Listar todos
    @Override
    public List<Line> findAll() {
        return lineRepository.findAll();
    }

    //Buscar por id
    @Override
    public Line findById(long id) throws LineNoFoundException{

        return lineRepository.findById(id)
                .orElseThrow(new LineNoFoundException::new);
    }
    //Añadir uno nuevo

    @Override
    public Line add(Line line) {
        return lineRepository.save(line);
    }

    @Override
    public void deleteLine(long id) throws LineNoFoundException{
        Line delLine = lineRepository.findById(id)
                        .orElseThrow(new LineNoFoundException::new);
        lineRepository.delete(delLine);
    }
}
