package com.juansenen.citytravel.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Line")

public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotNull(message = "El campo no puede estar vacio")
    @NotBlank(message = "El campo es necesario")
    private String code;
    @Column
    private boolean night;
    @Column
    private boolean wifi;
    @Column
    private String startloc;
    @Column
    private String stoploc;
    @Column
    private int period;

    //TODO Estudiar posibles validaciones
}
