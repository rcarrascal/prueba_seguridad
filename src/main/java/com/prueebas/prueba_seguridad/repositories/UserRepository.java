package com.prueebas.prueba_seguridad.repositories;

import com.prueebas.prueba_seguridad.models.User;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User,Long> {
    User findByUsername(final String username);
}
