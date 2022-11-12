package com.juansenen.citytravel.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "LineDamage")
public class LineDamage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotNull(message = "Campo no puede estar vacio")
    @NotBlank(message = "Campo debe rellenarse")
    private Date datestart;
    @Column
    @NotNull(message = "Campo no puede estar vacio")
    @NotBlank(message = "Campo debe rellenarse")
    private Date datefinish;

    //TODO Relacionar averias con lineas

}
