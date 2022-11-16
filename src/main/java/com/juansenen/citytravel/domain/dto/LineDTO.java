package com.juansenen.citytravel.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineDTO {

    private long lineId;
    private String codeLine;
    private String color;
    private LocalTime firsTime;
    private LocalTime lastTime;
    private int stopTime;
}
