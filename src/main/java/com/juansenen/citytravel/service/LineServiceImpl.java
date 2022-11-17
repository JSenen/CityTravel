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
    private LineRepository lineRepository;



    //Listar todos
    @Override
    public List<Line> findAll() {
        return lineRepository.findAll();
    }

    //Buscar por id
    @Override
    public Line findById(long id) throws LineNoFoundException {
        return lineRepository.findById(id)
                .orElseThrow(LineNoFoundException::new);
    }

    //Añadir uno nuevo
    @Override
    public Line add(Line line) throws LineNoFoundException{
        Line newLine = lineRepository.save(line);
        return newLine;
    }

    @Override
    public void deleteLine(long id) throws LineNoFoundException {
        Line delLine = lineRepository.findById(id)
                .orElseThrow(LineNoFoundException::new);
        lineRepository.deleteById(id);
    }

    @Override
    public Line modyLine(long id, Line line) throws LineNoFoundException{
        Line modyfLine = lineRepository.findById(id)
                .orElseThrow(LineNoFoundException::new);
        modyfLine.setCodeLine(line.getCodeLine());
        modyfLine.setColor(line.getColor());
        modyfLine.setStopTime(line.getStopTime());
        modyfLine.setFirstTime(line.getFirstTime());
        modyfLine.setLastTime(line.getLastTime());

        return lineRepository.save(modyfLine);

    }

}
