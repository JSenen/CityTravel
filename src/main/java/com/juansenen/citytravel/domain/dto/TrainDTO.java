package com.juansenen.citytravel.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainDTO {

    private String code;
    private String model;
    private int numWagons;
    private int numSeats;
    private int numStandUp;
    private int year;





}
