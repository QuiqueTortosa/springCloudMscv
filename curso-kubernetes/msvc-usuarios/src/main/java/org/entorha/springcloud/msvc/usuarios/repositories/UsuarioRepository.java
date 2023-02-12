package org.entorha.springcloud.msvc.usuarios.repositories;

import org.entorha.springcloud.msvc.usuarios.models.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository <Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.email=:email")
    Optional<Usuario> findByEmail(String email);

}
