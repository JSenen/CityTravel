package com.juansenen.citytravel.domain;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;


import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "line")

public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="code_line")
    private String codeLine;
    @Column
    private String color;
    @Column(name = "first_time")
    private LocalTime firstTime;
    @Column(name = "last_time")
    private LocalTime lastTime;
    @Column(name="stop_line")
    private int stopTime;

    @ManyToOne
    @JoinTable(name = "service",
            joinColumns = @JoinColumn(name="line_id"),
            inverseJoinColumns = @JoinColumn(name="train_id"))
    private List<LineTrain> lineTrainList;

    @ManyToMany
    @JoinTable(name="stops",
            joinColumns = @JoinColumn(name="line_id"),
            inverseJoinColumns = @JoinColumn(name="station_id"))
    private List<LineStation> lineStationList;



}
