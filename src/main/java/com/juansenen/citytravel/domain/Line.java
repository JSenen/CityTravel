package com.juansenen.citytravel.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Line")

public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String code;
    @Column
    private boolean night;
    @Column
    private boolean wifi;
    @Column
    private String startloc;
    @Column
    private String stoploc;
    @Column
    private int period;
}
