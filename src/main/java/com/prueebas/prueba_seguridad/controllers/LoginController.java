package com.prueebas.prueba_seguridad.controllers;

import com.prueebas.prueba_seguridad.models.dtos.UserDTO;
import com.prueebas.prueba_seguridad.security.CustomUserDetailsService;
import com.prueebas.prueba_seguridad.security.JwtGenerator;
import com.prueebas.prueba_seguridad.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtGenerator jwtGenerator;


    @PostMapping("api/v1/adminLogin")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO adminAuthDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(adminAuthDto.getUser(), adminAuthDto.getPass()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication,"ADMIN");
        UserDTO responseDto = new UserDTO();
        responseDto.setToken(token);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
