package com.juansenen.citytravel.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccesDTO {


    private String street;
    private String num;
    private float latitude;
    private float longitude;
    private boolean elevator;//
}
