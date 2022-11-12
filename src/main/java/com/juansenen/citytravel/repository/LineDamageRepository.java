package com.juansenen.citytravel.repository;

import com.juansenen.citytravel.domain.Line;
import com.juansenen.citytravel.domain.LineDamage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineDamageRepository extends CrudRepository<LineDamage, Long> {

    List<LineDamage> findAll();
}
