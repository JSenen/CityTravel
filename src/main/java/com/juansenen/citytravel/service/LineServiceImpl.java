package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.domain.dto.outLineDTO;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.exception.NotFoundException;
import com.juansenen.citytravel.repository.LineRepository;
import com.juansenen.citytravel.repository.LineStationRepository;
import com.juansenen.citytravel.repository.LineTrainRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

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

    @Override
    public List<outLineDTO> findAll() {
        List<Line> lines = lineRepository.findAll();
        List<outLineDTO> linesDTO = modelMapper.map(lines, new TypeToken<List<outLineDTO>>() {}.getType());
        return linesDTO;
    }

    //Buscar por id
    @Override
    public Line findById(long id) throws LineNoFoundException {
        return lineRepository.findById(id)
                .orElseThrow(()-> new LineNoFoundException());
    }

    @Override
    public List<outLineDTO> searchByHourStartAndHourClose(LocalTime start, LocalTime hclose) {
        List<Line> lines = lineRepository.findAllByHourStartOrHourClose(start, hclose);
        List<outLineDTO> linesDTO =  modelMapper.map(lines, new TypeToken<List<outLineDTO>>() {}.getType());

        return linesDTO;
    }
    @Override
    public Line add(Line line) {
        Line newLine = lineRepository.save(line);
        return newLine;
    }
    @Override
    public void deleteLine(long id) throws NotFoundException {
        Line delLine = lineRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(new Line()));
        lineRepository.delete(delLine);
    }

    @Override
    public Line modyLine(long id, Line line) throws NotFoundException {
        Line modyfLine = lineRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(new Line()));
        modyfLine.setCodeLine(line.getCodeLine());
        modyfLine.setColor(line.getColor());
        modyfLine.setStopTime(line.getStopTime());
        modyfLine.setFirstTime(line.getFirstTime());
        modyfLine.setLastTime(line.getLastTime());


        return lineRepository.save(modyfLine);

    }
    //Metodo actualización aprcial Patch
    @Override
    public Line updateLine(long id, Line line) throws LineNoFoundException {
        Line updateLine = lineRepository.findById(id)
                .orElseThrow(()-> new LineNoFoundException());
        updateLine.setCodeLine((line.getCodeLine()));
        return lineRepository.save(updateLine);
    }
}
