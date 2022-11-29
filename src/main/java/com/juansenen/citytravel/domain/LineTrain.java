package com.juansenen.citytravel.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "line_train")
public class LineTrain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code_train")
    private String code;
    @Column
    private String model;
    @Column(name="num_wagons")
    private int numWagons;
    @Column(name = "num_seats")
    private int numSeats;
    @Column(name = "num_stadup")
    private int numStandUp;
    @Column
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date datebuy;

    //Relacion trenes- garage M:1
    @ManyToOne
    @JoinColumn(name = "line_garage_id")
    @JsonIgnore
    private LineGarage garages;

    //Relación trenes - lineas M:1
    @ManyToOne
    @JoinColumn(name = "line_id")
    //@JsonBackReference
    @JsonIgnore
    private Line line;






}
