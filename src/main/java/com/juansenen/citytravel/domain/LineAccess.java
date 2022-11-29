package com.juansenen.citytravel.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "line_access")
public class LineAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String street;
    @Column
    private String num;
    @Column
    private float latitude;
    @Column
    private float longitude;
    @Column
    private boolean elevator;//

    //Relación Accesos - Estaciones M:1
    @ManyToOne
    @JoinColumn(name = "line_station_id")
    @JsonIgnore
    private LineStation stations;


}
