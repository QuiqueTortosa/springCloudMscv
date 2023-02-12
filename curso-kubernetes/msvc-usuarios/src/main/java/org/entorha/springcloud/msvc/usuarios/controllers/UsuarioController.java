package org.entorha.springcloud.msvc.usuarios.controllers;

import org.entorha.springcloud.msvc.usuarios.models.entity.Usuario;
import org.entorha.springcloud.msvc.usuarios.services.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping
    public List<Usuario> listar(){
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarUsuario(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        return usuarioOptional.isPresent() ?
                ResponseEntity.ok(usuarioOptional.get()) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        return usuarioService.saveUser(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarUsuario(@RequestBody Usuario usuario, @PathVariable Long id){
        Optional<Usuario> o = usuarioService.findById(id);
        usuario.setId(id);
        return o.isPresent() ?
                ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUser(usuario)) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        Optional<Usuario> o = usuarioService.findById(id);
        if(o.isPresent()) {
            usuarioService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
