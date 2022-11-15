package com.juansenen.citytravel.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Time;

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
    private String codeLine;
    @Column
    private String color;
    @Column
    private Time firstTime;
    @Column
    private Time lastTime;
    @Column
    private int stopTime;

    //TODO Estudiar posibles validaciones
}
