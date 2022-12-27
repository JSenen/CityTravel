package com.juansenen.citytravel.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class outTrainDTO {

    private long id;
    private String code;
    private String model;
}
