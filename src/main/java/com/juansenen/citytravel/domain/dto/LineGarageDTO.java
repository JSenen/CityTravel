package com.juansenen.citytravel.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineGarageDTO {

            private String codeGarage;
            private boolean taller;
            private float surface;
            private boolean rrhh;
            private boolean paintService;
            private long stationId;
}
