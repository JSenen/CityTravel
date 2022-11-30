package com.juansenen.citytravel.repository;

import com.juansenen.citytravel.domain.LineStation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineStationRepository extends CrudRepository<LineStation,Long> {

    List<LineStation> findAll();

}
