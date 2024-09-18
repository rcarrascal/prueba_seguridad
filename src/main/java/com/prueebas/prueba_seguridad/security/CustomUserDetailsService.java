package com.prueebas.prueba_seguridad.security;

import com.prueebas.prueba_seguridad.models.dtos.UserDTO;
import com.prueebas.prueba_seguridad.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            UserDTO userDTO = userService.findByUsername(username);

            SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority("ADMIN");
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(adminAuthority);
            return new User(userDTO.getUser(), userDTO.getPass(), authorities);

    }

}