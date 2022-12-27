package com.juansenen.citytravel.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.juansenen.citytravel.domain.LineAccess;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class outStationDTO {

    private long id;
    private String name;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime hopen;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime hclose;

}
