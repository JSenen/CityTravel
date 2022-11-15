package com.juansenen.citytravel.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "line_garage")
public class LineGarage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="code_garage")
    private String codeGarage;
    @Column
    private boolean taller;
    @Column
    private float surface;
    @Column
    private boolean rrhh;
    @Column(name="paint_service")
    private boolean paintService;

    //@OneToMany(mappedBy = "garage")
    //@JsonBackReference(value="garage_trains")
    //private List<LineTrain> lineTrains;

    //@ManyToOne
    //@JoinColumn(name = "id_station")
    //private LineStation lineStation;


}