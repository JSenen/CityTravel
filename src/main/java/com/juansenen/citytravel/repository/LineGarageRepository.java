package com.juansenen.citytravel.repository;

import com.juansenen.citytravel.domain.LineGarage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineGarageRepository extends CrudRepository<LineGarage,Long> {

    List<LineGarage> findAll();
    @Query( value = "SELECT * FROM line_garage WHERE taller=? and rrhh=? and paint_service=?", nativeQuery = true)
    List<LineGarage> findAllGarageWithTallerOrRrhhOrPaintService(boolean taller, boolean rrhh, boolean paintService);

}
