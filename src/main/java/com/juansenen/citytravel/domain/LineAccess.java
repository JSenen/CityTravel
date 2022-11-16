package com.juansenen.citytravel.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

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

    @ManyToOne
    @JoinColumn(name = "line_station_id")
    private LineStation lineStation;


}
