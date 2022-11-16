package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.domain.LineTrain;
import com.juansenen.citytravel.domain.dto.LineDTO;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.repository.LineRepository;
import com.juansenen.citytravel.repository.LineTrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineServiceImpl implements LineService {

    @Autowired
    private LineRepository lineRepository;

    @Autowired
    private LineTrainRepository lineTrainRepository;

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
    public Line add(LineDTO lineDTO) throws LineNoFoundException{
        Line newLine = new Line();
        newLine.setCodeLine(lineDTO.getCodeLine());
        newLine.setColor(lineDTO.getColor());
        newLine.setFirstTime(lineDTO.getFirsTime());
        newLine.setLastTime(lineDTO.getLastTime());
        newLine.setStopTime(lineDTO.getStopTime());

        LineTrain train = lineTrainRepository.findById(lineDTO.getLineId())
                .orElseThrow(LineNoFoundException::new);


        //TODO TERMINAR DTO

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
        modyfLine.setColor(line.getCodeLine());
        modyfLine.setStopTime(line.getStopTime());
        modyfLine.setFirstTime(line.getFirstTime());
        modyfLine.setLastTime(line.getLastTime());

        return lineRepository.save(modyfLine);

    }

}
