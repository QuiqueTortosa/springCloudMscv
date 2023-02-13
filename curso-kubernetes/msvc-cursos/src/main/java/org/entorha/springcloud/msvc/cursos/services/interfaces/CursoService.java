package org.entorha.springcloud.msvc.cursos.services.interfaces;

import org.entorha.springcloud.msvc.cursos.models.Usuario;
import org.entorha.springcloud.msvc.cursos.models.entities.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    List<Curso> findAll();
    Optional<Curso> findById(Long id);
    Curso save(Curso curso);
    void delete(Long id);

    Optional<Usuario> asignarUsuario(Long usuarioId, Long cursoId);
    Optional<Usuario> eliminarUsuario(Long usuarioId, Long cursoId);
    Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId);
}
