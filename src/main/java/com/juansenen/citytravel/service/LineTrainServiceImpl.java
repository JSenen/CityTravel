    package com.juansenen.citytravel.service;


    import com.juansenen.citytravel.domain.Line;
    import com.juansenen.citytravel.domain.LineGarage;
    import com.juansenen.citytravel.domain.LineStation;
    import com.juansenen.citytravel.domain.LineTrain;
    import com.juansenen.citytravel.domain.dto.inTrainDTO;
    import com.juansenen.citytravel.domain.dto.outTrainDTO;
    import com.juansenen.citytravel.exception.LineNoFoundException;

    import com.juansenen.citytravel.exception.NotFoundException;
    import com.juansenen.citytravel.repository.LineGarageRepository;
    import com.juansenen.citytravel.repository.LineRepository;
    import com.juansenen.citytravel.repository.LineTrainRepository;
    import org.modelmapper.ModelMapper;
    import org.modelmapper.TypeToken;
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
        private LineGarageRepository lineGarageRepository;
        @Autowired
        private ModelMapper modelMapper;


        @Override
        public List<outTrainDTO> findAll() {
            List<LineTrain> trains = lineTrainRepository.findAll();
            List<outTrainDTO> trainDTOS = modelMapper.map(trains, new TypeToken<List<outTrainDTO>>(){}.getType());
            return trainDTOS;
        }
        @Override
        public Optional<LineTrain> findById(long id) throws LineNoFoundException {
            return lineTrainRepository.findById(id);
        }
        @Override
        public List<outTrainDTO> searchByWagonsOrSeatsOrStandUp(int numWagons, int numSeats, int numStandUp) {
            List<LineTrain> trains = lineTrainRepository.findAllByWagonsOrSeatsOrStandUp(numWagons, numSeats, numStandUp);
            List<outTrainDTO> trainDTOS = modelMapper.map(trains, new TypeToken<List<outTrainDTO>>(){}.getType());
            return trainDTOS;
        }
        @Override
        public LineTrain delTrain(long id) throws NotFoundException {
            LineTrain delTrain = lineTrainRepository.findById(id)
                    .orElseThrow(()-> new NotFoundException(new LineTrain()));
            lineTrainRepository.deleteById(id);
            return delTrain;
        }

        @Override
        public LineTrain addNewTrain(long lineId, inTrainDTO inTrainDTO) throws NotFoundException {
            LineTrain newTrain = new LineTrain();

            modelMapper.map(inTrainDTO, newTrain);

            Line line = lineRepository.findById(lineId)
                    .orElseThrow(()-> new NotFoundException(new Line()));
            newTrain.setLine(line);
            return lineTrainRepository.save(newTrain);
        }

        @Override
        public LineTrain modTrain(long id,  LineTrain lineTrain) throws NotFoundException {

            LineTrain modtrain = lineTrainRepository.findById(id)
                    .orElseThrow(()-> new NotFoundException(new LineTrain()));

            modtrain.setCode(lineTrain.getCode());
            modtrain.setModel(lineTrain.getModel());
            modtrain.setNumSeats(lineTrain.getNumSeats());
            modtrain.setNumStandUp(lineTrain.getNumStandUp());
            modtrain.setNumWagons(lineTrain.getNumWagons());
            modtrain.setDatebuy(lineTrain.getDatebuy());

            return lineTrainRepository.save(modtrain);
        }

        @Override
        public List<LineTrain> findByLineId(long lineId) {
            return lineTrainRepository.findAllTrainsByLineId(lineId);
        }

        @Override
        public LineTrain updateOneTrain(long trainId, LineTrain lineTrain) throws NotFoundException {
            LineTrain updateTrain = lineTrainRepository.findById(trainId)
                    .orElseThrow(()-> new NotFoundException(new LineTrain()));
            updateTrain.setNumSeats(lineTrain.getNumSeats());
            updateTrain.setNumWagons(lineTrain.getNumWagons());
            updateTrain.setNumStandUp(lineTrain.getNumStandUp());

            return lineTrainRepository.save(updateTrain);
        }
    }
