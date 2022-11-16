package com.juansenen.citytravel.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineTrainDTO {

    private String model;
    private int numWagons;
    private int numSeats;
    private int numStandU;
    private int year;

    private long lineId;


}
