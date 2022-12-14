package com.juansenen.citytravel.repository;

import com.juansenen.citytravel.domain.LineAccess;
import com.juansenen.citytravel.domain.dto.outAccessDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineAccessRepository extends CrudRepository<LineAccess,Long> {

    List<LineAccess> findAll();

    List<LineAccess> findByElevator(boolean elevator);
}
