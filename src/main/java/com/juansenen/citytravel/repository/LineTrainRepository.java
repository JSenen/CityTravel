package com.juansenen.citytravel.repository;

import com.juansenen.citytravel.domain.LineTrain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineTrainRepository extends CrudRepository<LineTrain,Long> {

    List<LineTrain> findAll();
    List<LineTrain> findAllTrainsByLineId(long line);

    //Query para la introducion de parametros de filtrado
    @Query(value = "SELECT * FROM line_train WHERE num_wagons=? OR num_seats=? OR num_stadup=?"
            ,nativeQuery = true)
    List<LineTrain> findAllByWagonsOrSeatsOrStandUp(int numWagons, int numSeats, int numStandUp);

}
