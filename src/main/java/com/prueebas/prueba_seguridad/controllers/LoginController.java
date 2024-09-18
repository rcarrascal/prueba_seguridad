package com.prueebas.prueba_seguridad.controllers;

import com.prueebas.prueba_seguridad.models.dtos.UserDTO;
import com.prueebas.prueba_seguridad.security.JWTAuthtenticationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class LoginController {
    @Autowired
    JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @PostMapping("login")
    public UserDTO login(
            @RequestParam("user") String username,
            @RequestParam("password") String encryptedPass) {

        /**
         * En el ejemplo no se realiza la correcta validación del usuario
         */

        String token = jwtAuthtenticationConfig.getJWTToken(username);
        return new UserDTO(username, encryptedPass,token);

    }
}
