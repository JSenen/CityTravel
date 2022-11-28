package com.juansenen.citytravel.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.util.List;

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
    private int year;

    @ManyToOne
    @JoinColumn(name = "line_garage_id")
    private LineTrain lineTrain;

    @ManyToOne
    @JoinColumn(name = "line_id")
    private Line line;




}
