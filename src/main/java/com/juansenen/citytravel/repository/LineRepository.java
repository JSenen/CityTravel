package com.juansenen.citytravel.repository;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.domain.LineTrain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface LineRepository extends CrudRepository<Line, Long> {


    //Listar todos
    List<Line> findAll();

    @Query(value = "SELECT * FROM line WHERE first_time = ? OR last_time = ?",nativeQuery = true)
    List<Line> findAllByHourStartOrHourClose(LocalTime start, LocalTime close);
}
