package com.juansenen.citytravel.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class inStationDTO {

    private String name;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime hopen;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime hclose;
}
