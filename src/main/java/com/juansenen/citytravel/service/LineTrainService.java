    package com.juansenen.citytravel.service;

    import com.juansenen.citytravel.domain.LineTrain;
    import com.juansenen.citytravel.domain.dto.inTrainDTO;
    import com.juansenen.citytravel.domain.dto.outTrainDTO;
    import com.juansenen.citytravel.exception.LineNoFoundException;
    import com.juansenen.citytravel.exception.NotFoundException;

    import java.util.List;
    import java.util.Optional;

    public interface LineTrainService {

        List<outTrainDTO> findAll();
        Optional<LineTrain> findById(long id) throws LineNoFoundException;

        LineTrain delTrain(long id) throws NotFoundException;
        LineTrain addNewTrain(long lineId, inTrainDTO inTrainDTO) throws LineNoFoundException;
        LineTrain modTrain(long id, LineTrain lineTrain) throws NotFoundException;
        List<LineTrain> findByLineId(long lineId);
        List<outTrainDTO> searchByWagonsOrSeatsOrStandUp(int numWagons, int numSeats, int numStandUp);


        LineTrain updateOneTrain(long trainId, LineTrain lineTrain) throws NotFoundException;
    }
