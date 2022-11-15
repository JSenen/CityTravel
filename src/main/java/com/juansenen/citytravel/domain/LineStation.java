package com.juansenen.citytravel.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "line_station")
public class LineStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private LocalTime hopen;
    @Column
    private LocalTime hclose;
    @Column(name="pto_info")
    private boolean ptoInfo;
    @Column
    private boolean wifi;
    @Column(name="bus_station")
    private boolean busSation;
    @Column(name="taxi_station")
    private boolean taxiStation;

    //@OneToMany(mappedBy = "line_access")
    //@JsonBackReference(value = "linesation_access")
    //private List<LineStation> lineStationList;

    //@OneToMany(mappedBy = "line_garage")
    //@JsonBackReference(value = "linesation_garage")
    //private List<LineGarage> lineGarageList;

    //@ManyToMany(mappedBy = "line")
    //private List<Line> lineList;





}
