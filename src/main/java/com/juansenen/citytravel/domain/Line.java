package com.juansenen.citytravel.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "line")

public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 0, message = "Identifier must be integer") //Valor de entrada debe ser entero
    private long id;
    @Column(name="code_line")
    @NotBlank(message = "Must be filled")
    private String codeLine;
    @Column
    private String color;
    @Column(name = "first_time")
    @JsonFormat(pattern = "HH:mm")
    @NotNull
    private LocalTime firstTime;
    @Column(name = "last_time")
    @JsonFormat(pattern = "HH:mm")
    @NotNull
    private LocalTime lastTime;
    @Column(name="stop_line")
    private int stopTime;

    //Relación Lineas con Trenes
    @OneToMany(mappedBy = "line",cascade = CascadeType.REMOVE, orphanRemoval = true) //Para borrar en cascada
    private List<LineTrain> trains;

    @OneToMany(mappedBy = "line",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<LineStation> stations;




}
