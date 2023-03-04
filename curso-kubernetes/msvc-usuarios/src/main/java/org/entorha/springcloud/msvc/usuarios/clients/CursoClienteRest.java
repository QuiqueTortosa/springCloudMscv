package org.entorha.springcloud.msvc.usuarios.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

//Feign clientes tiene caracteristicas ya de loadbalancer si lo hemos añadido en el POM
@FeignClient(name = "msvc-cursos") //Ahora nos comunicamos mediante el nombre, coincide con el servicio de kubernetes y app.prop, url = "${msvc.cursos.url}"
public interface CursoClienteRest {

    @DeleteMapping("/curso/eliminar-curso-usuario/{id}")
    void eliminarCursoUsuarioPorId(@PathVariable Long id);

    }
