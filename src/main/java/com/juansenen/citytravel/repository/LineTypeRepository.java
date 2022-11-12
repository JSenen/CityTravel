package com.juansenen.citytravel.repository;

import com.juansenen.citytravel.domain.LineType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineTypeRepository extends CrudRepository<LineType, Long> {

    List<LineType> findAll();
}
