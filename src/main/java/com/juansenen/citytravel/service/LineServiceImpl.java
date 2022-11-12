package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        modyfLine.setCode(line.getCode());
        modyfLine.setPeriod(line.getPeriod());
        modyfLine.setStartloc(line.getStartloc());
        modyfLine.setStoploc(line.getStoploc());

        return lineRepository.save(modyfLine);


    }

    @Override
    public void modiLine(long id, Line line) {
        Line modline = findById(id);
        modline.setCode(line.getCode());
        modline.setNight(line.isNight());
        modline.setPeriod(line.getPeriod());
        modline.setStartloc(line.getStartloc());
        modline.setStoploc(line.getCode());
        modline.setWifi(line.isWifi());
        lineRepository.save(modline);

    }
}
