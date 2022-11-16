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

    @ManyToOne
    @JoinColumn(name = "line_station_id")
    private LineStation lineStation;

    @OneToMany
    @JoinColumn(name="linetrain")
    private List<LineTrain> lineTrainList;


}