package org.entorha.springcloud.msvc.cursos.controllers;

import org.entorha.springcloud.msvc.cursos.entities.Curso;
import org.entorha.springcloud.msvc.cursos.services.interfaces.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/")
    public ResponseEntity<?> crear(@RequestBody Curso curso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(curso));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> editar(@RequestBody Curso curso, @PathVariable Long id) {
        Optional<Curso> o = cursoService.findById(id);
        curso.setId(o.get().getId());
        return o.isPresent() ?
                ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(curso)) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Curso> o = cursoService.findById(id);
        if(o.isPresent()) {
            cursoService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
