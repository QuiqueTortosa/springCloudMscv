package org.entorha.springcloud.msvc.cursos.controllers;

import org.entorha.springcloud.msvc.cursos.entities.Curso;
import org.entorha.springcloud.msvc.cursos.services.interfaces.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
public class CursoController {

    private final CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listar() {
        return ResponseEntity.ok(cursoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Curso> o = cursoService.findById(id);
        return o.isPresent() ?
                ResponseEntity.ok(o.get()) :
                ResponseEntity.notFound().build();
    }


}
