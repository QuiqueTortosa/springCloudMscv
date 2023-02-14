package org.entorha.springcloud.msvc.usuarios.controllers;

import jakarta.validation.Valid;
import org.entorha.springcloud.msvc.usuarios.models.entity.Usuario;
import org.entorha.springcloud.msvc.usuarios.services.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @GetMapping("/usuarios-curso")
    public ResponseEntity<?> listarUsuariosPorCursos(@RequestParam List<Long> ids){
        return ResponseEntity.ok(usuarioService.findAllByIds(ids));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody Usuario usuario, BindingResult result){ //Resultado de la validacion -> BindingResult
        if(result.hasErrors()) {
            return validar(result);
        }
        if(usuarioService.findByEmail(usuario.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "User with that email already exists"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUser(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarUsuario(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id){
        if(result.hasErrors()) {
            return validar(result);
        }
        Optional<Usuario> o = usuarioService.findById(id);
        usuario.setId(id);
        if(!usuario.getEmail().equalsIgnoreCase(o.get().getEmail()) || usuarioService.findByEmail(usuario.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "User with that email already exists"));
        }
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

    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " "  + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }


}
