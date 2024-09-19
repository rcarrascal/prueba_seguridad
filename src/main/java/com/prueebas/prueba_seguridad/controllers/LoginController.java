package com.prueebas.prueba_seguridad.controllers;

import com.prueebas.prueba_seguridad.models.dtos.JWTResponse;
import com.prueebas.prueba_seguridad.models.dtos.UserDTO;
import com.prueebas.prueba_seguridad.security.CustomUserDetailsService;
import com.prueebas.prueba_seguridad.security.JwtGenerator;
import com.prueebas.prueba_seguridad.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtGenerator jwtGenerator;


    @PostMapping("api/user/login")
    public ResponseEntity<JWTResponse> login(@RequestBody UserDTO adminAuthDto) {

        try{
            log.info("Autenticamos usuario " + adminAuthDto);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(adminAuthDto.getUser(), adminAuthDto.getPass()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication,"ADMIN");

            return new ResponseEntity<>(new JWTResponse(LocalDateTime.now().plusDays(1),token), HttpStatus.OK);
        }catch (AuthenticationException ex){
            log.error("Error auntenticando usuario " , ex);
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }catch (Exception ex){
            log.error("Error interno auntenticando usuario " , ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
