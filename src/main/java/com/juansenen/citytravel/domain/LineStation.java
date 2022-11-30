package com.juansenen.citytravel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @JsonFormat(pattern = "HH:mm")
    private LocalTime hopen;
    @Column
    @JsonFormat(pattern = "HH:mm")
    private LocalTime hclose;
    @Column(name="pto_info")
    private boolean ptoInfo;
    @Column
    private boolean wifi;
    @Column(name="bus_station")
    private boolean busSation;
    @Column(name="taxi_station")
    private boolean taxiStation;

    @OneToMany(mappedBy = "lineStationAccess")
    private List<LineAccess> lineAccessList;

    @OneToMany(mappedBy = "lineStationGarage")
    private List<LineGarage> lineGarageList;

    @ManyToOne
    @JoinColumn(name = "station_id")
    @JsonIgnore
    private Line linestation;


}
