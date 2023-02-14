package org.entorha.springcloud.msvc.cursos.repositories;

import org.entorha.springcloud.msvc.cursos.models.entities.Curso;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface CursoRepository extends CrudRepository<Curso, Long> {

    @Modifying
    @Query("DELETE FROM CursoUsuario cu WHERE cu.usuarioId=:uId")
    void eliminarCursoUsuarioPorId(Long uId);

}
