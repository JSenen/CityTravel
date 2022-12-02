package com.juansenen.citytravel.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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
    @JoinColumn(name = "station_id")
    @JsonIgnore
    private LineStation lineStationGarage;

    @OneToMany
    @JoinColumn(name="garages")
    private List<LineTrain> lineTrainList;




}