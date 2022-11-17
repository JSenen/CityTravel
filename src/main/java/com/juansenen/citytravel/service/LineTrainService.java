package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.LineTrain;
import com.juansenen.citytravel.domain.dto.TrainDTO;
import com.juansenen.citytravel.exception.LineNoFoundException;

import java.util.List;
import java.util.Optional;

public interface LineTrainService {

    List<LineTrain> findAll();
    Optional<LineTrain> findById(long id) throws LineNoFoundException;
    LineTrain delTrain(long id) throws LineNoFoundException;
    LineTrain addTrain(TrainDTO trainDTO) throws LineNoFoundException;
    LineTrain modTrain(long id, LineTrain lineTrain) throws LineNoFoundException;
}
