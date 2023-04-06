package br.com.vip.springapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vip.springapi.exception.InvalidRequestException;
import br.com.vip.springapi.model.Usuario;
import br.com.vip.springapi.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }


    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario usuario) {
        Optional<Usuario> usuarioDB = usuarioRepository.findById(id);

           // usuarioDB.isPresent(u -> usuario.setId(u.getId()));
           
           if (usuarioDB.isPresent()) {
                usuario.setId(id);
               return usuarioRepository.save(usuario);
           } else {
            throw new InvalidRequestException("Usuario não encontrado!", null);
           }
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario findUsuarioById(Long id) {
        Optional<Usuario> usuarioEncontradoDB = usuarioRepository.findById(id);

        if (usuarioEncontradoDB.isPresent()) {
            return usuarioEncontradoDB.get();
        } else {
            throw new InvalidRequestException("Usuario não encontrado!", null);
        }
    }
 }
