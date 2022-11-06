package com.juansenen.citytravel.repository;

import com.juansenen.citytravel.domain.LineDamage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineDamageRepository extends CrudRepository<LineDamage, Long> {
}
