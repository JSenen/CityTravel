package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.domain.LineGarage;
import com.juansenen.citytravel.domain.LineStation;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.exception.StationNoFoundException;
import com.juansenen.citytravel.repository.LineRepository;
import com.juansenen.citytravel.repository.LineStationRepository;
import com.juansenen.citytravel.repository.LineTrainRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LineServiceImpl implements LineService {

    @Autowired
    private LineRepository lineRepository;
    @Autowired
    private LineTrainRepository lineTrainRepository;
    @Autowired
    private LineStationRepository lineStationRepository;
    @Autowired
    private ModelMapper modelMapper;



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
        lineRepository.delete(delLine);
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
