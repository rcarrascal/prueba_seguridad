package com.prueebas.prueba_seguridad.services;

import com.prueebas.prueba_seguridad.models.dtos.UserDTO;

public interface UserService {
    UserDTO findByUsername(String username);
}
