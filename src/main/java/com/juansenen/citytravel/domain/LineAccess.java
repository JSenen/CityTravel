package com.juansenen.citytravel.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private int num;
    @Column
    private float latitude;
    @Column
    private float longitude;
    @Column
    private boolean elevator;//

    @ManyToOne
    @JoinColumn(name = "station_id")
    @JsonBackReference
    private LineStation lineStationAccess;


}
