package com.juansenen.citytravel.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "LineInfo")
public class LineInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String code;
    @Column
    private String street;
    @Column
    private String phone;
    @Column
    private String mail;
    @Column
    private boolean saletickets;
}
