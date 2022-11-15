package com.juansenen.citytravel.repository;

import com.juansenen.citytravel.domain.Line;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LineRepository extends CrudRepository<Line, Long> {


    //Listar todos
    List<Line> findAll();
    


}
