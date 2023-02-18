package org.entorha.springcloud.msvc.cursos.clients;

import org.entorha.springcloud.msvc.cursos.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="msvc-usuarios", url = "${msvc.usuarios.url}")
public interface UsuarioClientRest {

    @GetMapping("/usuario/{id}")
    Usuario listarUsuario(@PathVariable Long id);

    @GetMapping("/usuario/usuarios-curso")
    List<Usuario> listarUsuariosPorCursos(@RequestParam Iterable<Long> ids);

    @PostMapping("/usuario/")
    Usuario crearUsuario(@RequestBody Usuario usuario);

}
