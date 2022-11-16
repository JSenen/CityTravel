package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.domain.LineTrain;
import com.juansenen.citytravel.domain.dto.LineTrainDTO;
import com.juansenen.citytravel.exception.LineNoFoundException;
import com.juansenen.citytravel.repository.LineRepository;
import com.juansenen.citytravel.repository.LineTrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LineTrainServiceImpl implements LineTrainService {

    @Autowired
    LineTrainRepository lineTrainRepository;


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

    @Override
    public LineTrain addTrain(LineTrain lineTRain) {

        return lineTrainRepository.save(lineTRain);
    }

    @Override
    public LineTrain modTrain(long id, LineTrain lineTrain) throws LineNoFoundException {
        LineTrain modTrain = lineTrainRepository.findById(id).
                orElseThrow(LineNoFoundException::new);
        modTrain.setModel(lineTrain.getModel());
        modTrain.setNumSeats(lineTrain.getNumSeats());
        modTrain.setNumStandUp(lineTrain.getNumStandUp());
        modTrain.setNumWagons(lineTrain.getNumWagons());
        modTrain.setYear(lineTrain.getYear());

        return lineTrainRepository.save(modTrain);
    }
}
