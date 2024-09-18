package com.prueebas.prueba_seguridad.repositories;

import com.prueebas.prueba_seguridad.models.entities.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserRepository extends ListCrudRepository<User,Long> {
    Optional<User> findByUsername(final String username);
}
