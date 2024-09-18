package com.prueebas.prueba_seguridad.services.impl;

import com.prueebas.prueba_seguridad.models.dtos.UserDTO;
import com.prueebas.prueba_seguridad.repositories.UserRepository;
import com.prueebas.prueba_seguridad.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO findByUsername(String username) {
        return userRepository.findByUsername(username).map(user -> new UserDTO(user.getUsername(),user.getPassword(),""))
                .get();
    }
}
