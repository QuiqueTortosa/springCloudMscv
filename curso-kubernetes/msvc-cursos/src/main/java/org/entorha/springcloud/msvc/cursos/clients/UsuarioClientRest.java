package org.entorha.springcloud.msvc.cursos.clients;

import org.entorha.springcloud.msvc.cursos.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="msvc-usuarios", url = "localhost:8001")
public interface UsuarioClientRest {

    @GetMapping("/usuario/{id}")
    Usuario listarUsuario(@PathVariable Long id);

    @PostMapping("/usuario/")
    Usuario crearUsuario(@RequestBody Usuario usuario);

}
