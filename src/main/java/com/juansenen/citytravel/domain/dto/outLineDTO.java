package com.juansenen.citytravel.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.juansenen.citytravel.domain.LineStation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class outLineDTO {

    private String codeLine;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime firstTime;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime lastTime;
    private List<LineStation> stations;
}
