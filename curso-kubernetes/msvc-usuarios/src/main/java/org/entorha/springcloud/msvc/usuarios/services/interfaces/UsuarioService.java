package org.entorha.springcloud.msvc.usuarios.services.interfaces;

import org.entorha.springcloud.msvc.usuarios.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> findAll();

    Optional<Usuario> findById(Long id);

    List<Usuario> findAllByIds(Iterable<Long> ids);

    Optional<Usuario> findByEmail(String email);

    Usuario saveUser(Usuario usuario);

    void deleteUser(Long id);
}
