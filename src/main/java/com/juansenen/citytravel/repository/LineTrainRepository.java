package com.juansenen.citytravel.repository;

import com.juansenen.citytravel.domain.LineTrain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineTrainRepository extends CrudRepository<LineTrain,Long> {

    List<LineTrain> findAll();
    List<LineTrain> findAllTrainsByLineId(long line);
}
