package com.prueebas.prueba_seguridad.services.impl;

import com.prueebas.prueba_seguridad.models.dtos.UserDTO;
import com.prueebas.prueba_seguridad.models.entities.User;
import com.prueebas.prueba_seguridad.repositories.UserRepository;
import com.prueebas.prueba_seguridad.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO findByUsername(String username) {
        log.info("Obtenemos usuario " + username);
        return userRepository.findByUsername(username).map(user -> new UserDTO(user.getUsername(),user.getPassword(),""))
                .get();
    }

    @Override
    public UserDTO create(UserDTO user) {
        User userEntity= new User();
        userEntity.setUsername(user.getUser());
        userEntity.setPassword(user.getPass());
        User userResponse=userRepository.save(userEntity);
        return new UserDTO(userResponse.getUsername(),userResponse.getPassword(),"");
    }
}
