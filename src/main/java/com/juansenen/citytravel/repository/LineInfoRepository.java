package com.juansenen.citytravel.repository;

import com.juansenen.citytravel.domain.LineInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineInfoRepository extends CrudRepository<LineInfo, Long> {

    List<LineInfo> findAll();
}
