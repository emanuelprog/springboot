package br.com.vip.springapi.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vip.springapi.exception.InvalidRequestException;
import br.com.vip.springapi.model.DefaultResponse;
import br.com.vip.springapi.model.Usuario;
import br.com.vip.springapi.service.UsuarioService;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {
    
    public List<Usuario> usuarios = new ArrayList<Usuario>();
    public Long geradorId = 0L;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<DefaultResponse> adicionar(@RequestBody Usuario usuario) {
        Usuario usuarioNovo = usuarioService.saveUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DefaultResponse(201, "Usuario adicionado com sucesso!", usuarioNovo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> buscarPorId(@PathVariable Long id) {
        Usuario usuarioEncontrado = usuarioService.findUsuarioById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(200, "Usuario encontrado com sucesso!", usuarioEncontrado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> remover(@PathVariable Long id) {
        try {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(200, "Usuario deletado com sucesso!", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DefaultResponse(400, "Usuario não encontrado!", null));
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> atualizar(@PathVariable Long id,@RequestBody Usuario usuario){
        try {
            Usuario usuarioRetorno = usuarioService.updateUsuario(id, usuario);
            return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(200, "Usuario atualizado com sucesso!", usuarioRetorno));
        } catch (InvalidRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DefaultResponse(400, "Usuario não encontrado!", null));
        }
    }

    @GetMapping
    public ResponseEntity<DefaultResponse> buscarTodos() {
        List<Usuario> usuarios = usuarioService.getUsuarios();
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(200, "Lista encontrada!", usuarios));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DefaultResponse(400, "Lista não encontrada", null));
        }
    }

    // @PatchMapping("/{id}")
    // public void atualizarAtivo(@PathVariable Long id, @RequestBody Usuario usuario) {
    //     Usuario usuarioAtualizado = null;
    //     for (Usuario user : usuarios) {
    //         if (user.getId() == id) {
    //             usuarioAtualizado = user;
    //         }
    //     }
    //     if (usuarioAtualizado.getAtivo() != true) {
    //         usuarioAtualizado.setAtivo(true);
    //     }
    // }
}
