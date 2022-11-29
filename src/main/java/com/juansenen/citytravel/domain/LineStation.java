package com.juansenen.citytravel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @OneToMany(mappedBy = "lineStation")
    private List<LineAccess> lineAccessList;

    @OneToMany(mappedBy = "lineStation")
    private List<LineGarage> lineGarageList;

    @ManyToMany
    @JoinTable(name = "stops",
            joinColumns = @JoinColumn(name = "station_id"),
            inverseJoinColumns = @JoinColumn(name = "line_id"))
    private List<Line> lineList;


}
