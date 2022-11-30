package com.juansenen.citytravel.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;


import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @JsonFormat(pattern = "HH:mm")
    private LocalTime firstTime;
    @Column(name = "last_time")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime lastTime;
    @Column(name="stop_line")
    private int stopTime;

    @OneToMany(mappedBy = "line",cascade = CascadeType.REMOVE, orphanRemoval = true) //Para borrar en cascada
    //@JsonManagedReference
    private List<LineTrain> trains;

    @OneToMany(mappedBy = "linestation")
    private List<LineStation> stations;




}
