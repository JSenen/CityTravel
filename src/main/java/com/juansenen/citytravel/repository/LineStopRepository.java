package com.juansenen.citytravel.repository;

import com.juansenen.citytravel.domain.LineStop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineStopRepository extends CrudRepository<LineStop, Long> {

    List<LineStop> findAll();
}
