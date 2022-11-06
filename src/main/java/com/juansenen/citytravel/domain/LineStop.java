package com.juansenen.citytravel.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "LineStop")
public class LineStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String street;
    @Column
    private long latitude;
    @Column
    private long longitude;

    //TODO Relacionar paradas con lineas

}
