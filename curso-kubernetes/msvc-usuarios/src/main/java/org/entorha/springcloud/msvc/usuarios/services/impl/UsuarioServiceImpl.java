package org.entorha.springcloud.msvc.usuarios.services.impl;

import org.entorha.springcloud.msvc.usuarios.clients.CursoClienteRest;
import org.entorha.springcloud.msvc.usuarios.models.entity.Usuario;
import org.entorha.springcloud.msvc.usuarios.repositories.UsuarioRepository;
import org.entorha.springcloud.msvc.usuarios.services.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CursoClienteRest cursoClient;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, CursoClienteRest cursoClient) {
        this.usuarioRepository = usuarioRepository;
        this.cursoClient = cursoClient;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAllByIds(Iterable<Long> ids) {
        return (List<Usuario>) usuarioRepository.findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public Usuario saveUser(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        usuarioRepository.deleteById(id);
        cursoClient.eliminarCursoUsuarioPorId(id);
    }
}
