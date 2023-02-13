package org.entorha.springcloud.msvc.cursos.services.impls;

import org.entorha.springcloud.msvc.cursos.clients.UsuarioClientRest;
import org.entorha.springcloud.msvc.cursos.models.Usuario;
import org.entorha.springcloud.msvc.cursos.models.entities.Curso;
import org.entorha.springcloud.msvc.cursos.models.entities.CursoUsuario;
import org.entorha.springcloud.msvc.cursos.repositories.CursoRepository;
import org.entorha.springcloud.msvc.cursos.services.interfaces.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;
    private final UsuarioClientRest clientRest;

    @Autowired
    public CursoServiceImpl(CursoRepository cursoRepository, UsuarioClientRest clientRest) {
        this.cursoRepository = cursoRepository;
        this.clientRest = clientRest;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Curso> findAll() {
        return (List<Curso>) cursoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findById(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    @Transactional
    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cursoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Usuario> asignarUsuario(Long usuarioId, Long cursoId) {
        Optional<Curso> o = cursoRepository.findById(cursoId);
        if(o.isPresent()) {
            Usuario usuarioMsvc = clientRest.listarUsuario(usuarioId);

            Curso curso =  o.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioMsvc.getId());

            curso.addCursoUsuario(cursoUsuario);
            cursoRepository.save(curso);

            return Optional.of(usuarioMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId) {
        Optional<Curso> o = cursoRepository.findById(cursoId);
        if(o.isPresent()) {
            Usuario usuarioNuevoMsvc = clientRest.crearUsuario(usuario);

            Curso curso =  o.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioNuevoMsvc.getId());

            curso.addCursoUsuario(cursoUsuario);
            cursoRepository.save(curso);

            return Optional.of(usuarioNuevoMsvc);
        }
        return Optional.empty();    }

    @Override
    @Transactional
    public Optional<Usuario> eliminarUsuario(Long usuarioId, Long cursoId) {
        Optional<Curso> o = cursoRepository.findById(cursoId);
        if(o.isPresent()) {
            Usuario usuarioMsvc = clientRest.listarUsuario(usuarioId);

            Curso curso =  o.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioMsvc.getId());

            curso.removeCursoUsuario(cursoUsuario);
            cursoRepository.save(curso);

            return Optional.of(usuarioMsvc);
        }
        return Optional.empty();
    }
}
