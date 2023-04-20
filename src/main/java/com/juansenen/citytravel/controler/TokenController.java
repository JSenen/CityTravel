package com.juansenen.citytravel.controler;


import com.juansenen.citytravel.domain.dto.UserDTO;
import com.juansenen.citytravel.security.JwtResponse;
import com.juansenen.citytravel.security.JwtUtils;
import com.juansenen.citytravel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TokenController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/token")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserDTO userDTO) {

        if (!isValidUser(userDTO)) {
            return ResponseEntity.badRequest().build(); // Devuelve un código 400 Bad Request si el objeto UserDTO no es válido
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();
        //Comprobacion de roles
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getUsername()));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        if (!isValidUser(user)){
            return ResponseEntity.badRequest().body("El objeto no es valido");
        }
        return ResponseEntity.ok(userService.addUser(user));
    }
    /** Validaciones del body /register */
    private boolean isValidUser(UserDTO user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            return false; // El campo de nombre de usuario está vacío
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()){
            return false; //Campo password está vacio
        }
        return true;
    }

}
