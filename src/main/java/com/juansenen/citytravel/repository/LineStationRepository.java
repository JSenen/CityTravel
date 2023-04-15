package com.juansenen.citytravel.repository;

import com.juansenen.citytravel.domain.LineStation;
import com.juansenen.citytravel.domain.dto.outStationDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineStationRepository extends CrudRepository<LineStation,Long> {

    List<LineStation> findAll();
    List<LineStation> findAllStationsByLineId(long lineId);
    @Query( value = "SELECT * FROM \"line_station\" WHERE wifi=? and bus_station=? and taxi_station=? and pto_info=?", nativeQuery = true)
    List<LineStation> findByWifiOrBusStationOrTaxiStation(boolean wifi, boolean busStation, boolean taxiStation,boolean ptoInfo);

    @Query( value = "SELECT * FROM \"line_station\" WHERE line_id=? and wifi=? and bus_station=? and taxi_station=? and pto_info=?", nativeQuery = true)
    List<LineStation> findStationsByParams(long line_id, boolean wifi, boolean busStation, boolean taxiStation, boolean ptoInfo);




}
