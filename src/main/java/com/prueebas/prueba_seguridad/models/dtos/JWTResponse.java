package com.prueebas.prueba_seguridad.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class JWTResponse {
    private LocalDateTime expired;
    private String token;
}
