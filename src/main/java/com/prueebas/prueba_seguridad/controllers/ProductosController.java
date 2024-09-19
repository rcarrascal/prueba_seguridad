package com.prueebas.prueba_seguridad.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductosController {

    @GetMapping("/api/productos")
    public String prueba(){
        return "prueba";
    }
}
