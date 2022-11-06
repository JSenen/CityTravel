package com.juansenen.citytravel.repository;

import com.juansenen.citytravel.domain.Line;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineRepository extends CrudRepository<Line, Long> {
}
