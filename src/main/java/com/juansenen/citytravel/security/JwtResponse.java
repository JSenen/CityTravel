package com.juansenen.citytravel.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/** Respuesta al token */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private String token;
    private String username;

}
