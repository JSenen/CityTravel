package com.juansenen.citytravel.service;


import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.domain.LineTrain;
import com.juansenen.citytravel.domain.dto.TrainDTO;
import com.juansenen.citytravel.exception.LineNoFoundException;

import com.juansenen.citytravel.repository.LineRepository;
import com.juansenen.citytravel.repository.LineTrainRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LineTrainServiceImpl implements LineTrainService {

    @Autowired
    private LineTrainRepository lineTrainRepository;
    @Autowired
    private LineRepository lineRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<LineTrain> findAll() {

        return lineTrainRepository.findAll();
    }

    @Override
    public Optional<LineTrain> findById(long id) throws LineNoFoundException {
        return lineTrainRepository.findById(id);
    }

    @Override
    public LineTrain delTrain(long id) throws LineNoFoundException {
        LineTrain delTrain = lineTrainRepository.findById(id)
                .orElseThrow(LineNoFoundException::new);
        lineTrainRepository.deleteById(id);
        return delTrain;
    }
    //TODO terminar DTO
    @Override
    public LineTrain addNewTrain(TrainDTO trainDTO) throws LineNoFoundException {
        LineTrain newTrain = new LineTrain();

        modelMapper.map(trainDTO, newTrain);

        Line line = lineRepository.findById(trainDTO.getLineId())
                .orElseThrow(LineNoFoundException::new);
        newTrain.setLine(line);
        return lineTrainRepository.save(newTrain);
    }

    @Override
    public LineTrain modTrain(long id, LineTrain lineTrain) throws LineNoFoundException {

        LineTrain modtrain = lineTrainRepository.findById(id)
                .orElseThrow(LineNoFoundException::new);
        modtrain.setCode(lineTrain.getCode());
        modtrain.setModel(lineTrain.getModel());
        modtrain.setNumSeats(lineTrain.getNumSeats());
        modtrain.setNumStandUp(lineTrain.getNumStandUp());
        modtrain.setNumWagons(lineTrain.getNumWagons());
        modtrain.setYear(lineTrain.getYear());

        return lineTrainRepository.save(modtrain);
    }
}
