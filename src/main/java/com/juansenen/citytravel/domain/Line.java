package com.juansenen.citytravel.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "line")

public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="code_line")
    @NotNull(message = "El campo no puede estar vacio")
    @NotBlank(message = "El campo es necesario")
    private String codeLine;
    @Column
    private String color;
    @Column(name = "first_time")
    private LocalTime firstTime;
    @Column(name = "last_time")
    private LocalTime lastTime;
    @Column(name="stop_line")
    private int stopTime;

    //TODO revisar relaciones todas las clases

    //@ManyToMany
    //@JoinTable(name = "services",
    //        joinColumns = @JoinColumn(name = "id_line"),
    //        inverseJoinColumns = @JoinColumn(name="id_train"))
    //private List<LineTrain> lineTrainList;

    //@ManyToMany
    //@JoinTable(name="stops",
    //        joinColumns = @JoinColumn(name = "id_line"),
    //        inverseJoinColumns = @JoinColumn(name="id_station"))
    //private List<LineStation> lineStationList;

}
