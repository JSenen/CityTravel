package com.juansenen.citytravel.repository;

import com.juansenen.citytravel.domain.LineGarage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineGarageRepository extends CrudRepository<LineGarage,Long> {

    List<LineGarage> findAll();
}
